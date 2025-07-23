package br.com.fiap.gerenciatarefas.adapters.outbound.JPA.repositories;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.TasksJPA;
import br.com.fiap.gerenciatarefas.core.tasks.port.TasksRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TasksRepositoryImpl implements TasksRepositoryPort
{
    @Autowired
    private final JpaTasksRepository jpaTasksRepository;

    public TasksRepositoryImpl(JpaTasksRepository jpaTasksRepository) {
        this.jpaTasksRepository = jpaTasksRepository;
    }

    @Override
    public void createTask(TasksJPA tasksJPA)
    {
        this.jpaTasksRepository.save(tasksJPA);
    }

    @Override
    public void updateTask(TasksJPA tasksJPA) {
        this.jpaTasksRepository.save(tasksJPA);
    }

    @Override
    public void deleteTask(Long id) {
        this.jpaTasksRepository.deleteById(id);
    }

    @Override
    public TasksJPA findTaskById(Long id) {
        return this.jpaTasksRepository.findById(id).orElse(null);
    }

    @Override
    public Page<TasksJPA> findAllTasksByUsername(Pageable pageable, String username) {
        Page<TasksJPA> allTasks =  this.jpaTasksRepository.findAllTasksByUserId_Login(pageable, username);
        return allTasks;
    }


}
