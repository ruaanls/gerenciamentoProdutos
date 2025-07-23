package br.com.fiap.gerenciatarefas.application.service;
import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import br.com.fiap.gerenciatarefas.adapters.outbound.security.TokenServicePort;
import br.com.fiap.gerenciatarefas.application.usecases.UserUsecases;
import br.com.fiap.gerenciatarefas.core.user.DTO.AuthenticationRequestDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.LoginResponseDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.RegisterDTO;
import br.com.fiap.gerenciatarefas.core.user.port.UserRepositoryPort;
import br.com.fiap.gerenciatarefas.infra.security.Exception.RegisterFailedException;
import br.com.fiap.gerenciatarefas.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserUsecases {

    @Autowired
    private final UserRepositoryPort userRepositoryPort;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenServicePort tokenService;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort, UserMapper userMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
    }

    @Override
    public void registerUser(RegisterDTO registerDTO) {
        if(userRepositoryPort.findByLogin(registerDTO.getLogin()) != null)
        {
            throw new RegisterFailedException();
        }
        String passwordEncrypted = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        UserJpa userJpa = userMapper.requestToUser(registerDTO,passwordEncrypted);
        this.userRepositoryPort.registerUser(userJpa);
    }

    @Override
    public LoginResponseDTO login(AuthenticationRequestDTO request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
        var auth = authManager.authenticate(usernamePassword);
        UserJpa login = (UserJpa) auth.getPrincipal();
        var token = this.tokenService.generateToken(login);
        return this.userMapper.requestToLogin(token);
    }


}
