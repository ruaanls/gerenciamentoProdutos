package br.com.fiap.gerenciatarefas.application.usecases;

import br.com.fiap.gerenciatarefas.core.user.DTO.AuthenticationRequestDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.LoginResponseDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.RegisterDTO;

public interface UserUsecases
{
    void registerUser(RegisterDTO registerDTO);
    LoginResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO);
}
