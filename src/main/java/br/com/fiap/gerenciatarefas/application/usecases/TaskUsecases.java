package br.com.fiap.gerenciatarefas.application.usecases;

import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskRequestDTO;
import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskUsecases
{
    void createTask(TaskRequestDTO taskRequestDTO);
    void updateTask(TaskRequestDTO taskRequestDTO, Long id);
    void deleteTask(Long id);
    TaskResponseDTO findTaskById(Long id);
    Page<TaskResponseDTO> findAllTasks(Pageable pageable, String username);

}
