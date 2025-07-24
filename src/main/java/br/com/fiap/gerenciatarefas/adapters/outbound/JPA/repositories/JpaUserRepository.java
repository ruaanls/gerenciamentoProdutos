package br.com.fiap.gerenciatarefas.adapters.outbound.JPA.repositories;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaUserRepository extends JpaRepository<UserJpa, String>
{
    UserDetails findByLogin(String login);
    Optional<UserJpa> findUserJpaByLogin(String login);
}
