package br.com.fiap.gerenciatarefas.adapters.outbound.repositories;

import br.com.fiap.gerenciatarefas.adapters.outbound.entities.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserJpa, String> {
}
