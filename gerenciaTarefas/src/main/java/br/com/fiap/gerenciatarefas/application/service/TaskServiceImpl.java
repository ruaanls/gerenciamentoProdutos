package br.com.fiap.gerenciatarefas.application.service;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.TasksJPA;
import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import br.com.fiap.gerenciatarefas.application.usecases.TaskUsecases;
import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskRequestDTO;
import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskResponseDTO;
import br.com.fiap.gerenciatarefas.core.tasks.port.TasksRepositoryPort;
import br.com.fiap.gerenciatarefas.core.user.port.UserRepositoryPort;
import br.com.fiap.gerenciatarefas.utils.mapper.TasksMapper;
import br.com.fiap.gerenciatarefas.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskUsecases
{
    @Autowired
    private final TasksRepositoryPort tasksRepository;
    @Autowired
    private final UserRepositoryPort userRepositoryPort;

    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final TasksMapper tasksMapper;

    public TaskServiceImpl(TasksRepositoryPort tasksRepository, UserRepositoryPort userRepositoryPort, UserMapper userMapper, TasksMapper tasksMapper) {
        this.tasksRepository = tasksRepository;
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
        this.tasksMapper = tasksMapper;
    }

    @Override
    public void createTask(TaskRequestDTO taskRequestDTO) {
        Optional<UserJpa> user = this.userRepositoryPort.findUserJpaByLogin(taskRequestDTO.getUsername());
        UserJpa userJpa = user.map(this.userMapper::optionalToUser).orElse(null);
        TasksJPA tasksJPA = this.tasksMapper.requestToTask(taskRequestDTO,userJpa);
        this.tasksRepository.createTask(tasksJPA);
    }

    @Override
    public void updateTask(TaskRequestDTO taskRequestDTO, Long id) {
        TasksJPA taskAtual = this.tasksRepository.findTaskById(id);
        Optional<UserJpa> user = this.userRepositoryPort.findUserJpaByLogin(taskRequestDTO.getUsername());
        UserJpa userJpa = user.map(this.userMapper::optionalToUser).orElse(null);
        TasksJPA tasksJPA = this.tasksMapper.requestToTask(taskRequestDTO,userJpa);
        tasksJPA.setId(taskAtual.getId());
        this.tasksRepository.updateTask(tasksJPA);
    }

    @Override
    public void deleteTask(Long id) {

        this.tasksRepository.deleteTask(id);
    }

    @Override
    public TaskResponseDTO findTaskById(Long id) {

        TasksJPA task = this.tasksRepository.findTaskById(id);
        TaskResponseDTO taskResponseDTO = this.tasksMapper.taskToResponse(task);
        return taskResponseDTO;
    }

    @Override
    public Page<TaskResponseDTO> findAllTasks(Pageable pageable, String username) {
        Page<TasksJPA> tasks = this.tasksRepository.findAllTasksByUsername(pageable,username);
        Page<TaskResponseDTO> taskResponseDTOS = tasks.map(this.tasksMapper::taskToResponse);
        return taskResponseDTOS;
    }
}
