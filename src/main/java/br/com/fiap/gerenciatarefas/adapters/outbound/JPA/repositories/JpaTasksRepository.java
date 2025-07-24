package br.com.fiap.gerenciatarefas.adapters.outbound.JPA.repositories;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.TasksJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTasksRepository extends JpaRepository<TasksJPA, Long>
{
    
    Page<TasksJPA> findAllTasksByUserId_Login(Pageable pageable, String username);
}
