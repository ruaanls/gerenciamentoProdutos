package br.com.fiap.gerenciatarefas.adapters.inbound.controller;

import br.com.fiap.gerenciatarefas.adapters.outbound.entities.UserJpa;
import br.com.fiap.gerenciatarefas.adapters.outbound.repositories.JpaUserRepository;
import br.com.fiap.gerenciatarefas.core.user.DTO.AuthenticationRequestDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.LoginResponseDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.RegisterDTO;
import br.com.fiap.gerenciatarefas.core.user.User;
import br.com.fiap.gerenciatarefas.infra.security.Services.TokenService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController
{
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO data)
    {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = authManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((UserJpa) auth.getPrincipal());
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(token);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data)
    {
        if(this.userRepository.findByLogin(data.getLogin()) != null)
        {
            // Se existir algum usuário retornar badrequest
            // NULL = Não existe usuário pronto para registrar
            throw new RuntimeException();
        }
        // Criptografar a senha
        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.getPassword());
        // Usuário salvo no banco de dados com a senha criptografada
        UserJpa newUser = new UserJpa(data.getLogin(), senhaCriptografada, data.getUser_role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
