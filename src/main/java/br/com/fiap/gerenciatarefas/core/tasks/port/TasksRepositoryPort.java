package br.com.fiap.gerenciatarefas.core.tasks.port;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.TasksJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TasksRepositoryPort
{
    void createTask(TasksJPA tasksJPA);
    void updateTask(TasksJPA tasksJPA);
    void deleteTask(Long id);
    TasksJPA findTaskById(Long id);
    Page<TasksJPA> findAllTasksByUsername(Pageable pageable, String username);
}
