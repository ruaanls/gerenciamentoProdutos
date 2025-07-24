package br.com.fiap.gerenciatarefas.adapters.outbound.JPA.repositories;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.TasksJPA;
import br.com.fiap.gerenciatarefas.core.tasks.port.TasksRepositoryPort;
import br.com.fiap.gerenciatarefas.infra.security.Exception.NoTasksFindException;
import br.com.fiap.gerenciatarefas.infra.security.Exception.TaskNotFoundException;
import br.com.fiap.gerenciatarefas.infra.security.Exception.UserNotFoundException;
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

    @Autowired
    private final JpaUserRepository jpaUserRepository;

    public TasksRepositoryImpl(JpaTasksRepository jpaTasksRepository, JpaUserRepository jpaUserRepository) {
        this.jpaTasksRepository = jpaTasksRepository;
        this.jpaUserRepository = jpaUserRepository;
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
        if(!this.jpaTasksRepository.existsById(id))
        {
            throw new TaskNotFoundException("Task não encontrada com o id: " + id + " Por favor passe um id de uma task existente");
        }
        this.jpaTasksRepository.deleteById(id);
    }

    @Override
    public TasksJPA findTaskById(Long id)
    {
        return this.jpaTasksRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task não encontrada com o id: " + id + " Por favor passe um id de uma task existente"));
    }

    @Override
    public Page<TasksJPA> findAllTasksByUsername(Pageable pageable, String username) {
        if(this.jpaUserRepository.findUserJpaByLogin(username).isEmpty())
        {
            throw new UserNotFoundException();
        }
        else{
            Page<TasksJPA> allTasks =  this.jpaTasksRepository.findAllTasksByUserId_Login(pageable, username);
            if(allTasks.getTotalElements() == 0L)
            {
                throw new NoTasksFindException();
            }
            else
            {
                return allTasks;
            }

        }


    }


}
