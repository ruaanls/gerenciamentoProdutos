package br.com.fiap.gerenciatarefas.core.user.port;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepositoryPort
{
    void registerUser(UserJpa user);
    UserDetails findByLogin(String login);
}
