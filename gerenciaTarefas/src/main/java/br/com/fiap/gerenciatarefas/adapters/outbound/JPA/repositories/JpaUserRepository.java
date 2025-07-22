package br.com.fiap.gerenciatarefas.adapters.outbound.JPA.repositories;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


// TALVEZ PRECISE DE UM @REPOSITORY AQUI TBM
public interface JpaUserRepository extends JpaRepository<UserJpa, String>
{
    UserDetails findByLogin(String login);
}
