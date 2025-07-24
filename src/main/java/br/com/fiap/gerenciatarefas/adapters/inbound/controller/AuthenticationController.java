package br.com.fiap.gerenciatarefas.adapters.inbound.controller;
import br.com.fiap.gerenciatarefas.application.usecases.UserUsecases;
import br.com.fiap.gerenciatarefas.core.user.DTO.AuthenticationRequestDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.LoginResponseDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController
{

    @Autowired
    private UserUsecases userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO data)
    {
        LoginResponseDTO loginResponse =  this.userService.login(data);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data)
    {
        this.userService.registerUser(data);
        return ResponseEntity.ok().build();
    }



}
