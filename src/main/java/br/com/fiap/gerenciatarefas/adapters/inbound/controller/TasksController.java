package br.com.fiap.gerenciatarefas.adapters.inbound.controller;

import br.com.fiap.gerenciatarefas.application.usecases.TaskUsecases;
import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskRequestDTO;
import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController
{
    @Autowired
    private TaskUsecases taskService;

    @PostMapping("/create")
    public ResponseEntity createTask(@RequestBody @Valid TaskRequestDTO request)
    {
        this.taskService.createTask(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findAllTasks( @Valid @PathVariable Long id)
    {
        TaskResponseDTO taskResponse = this.taskService.findTaskById(id);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@RequestBody @Valid TaskRequestDTO request, @PathVariable Long id)
    {
        this.taskService.updateTask(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id)
    {
        this.taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<Page<TaskResponseDTO>> findAllTasks(@RequestParam(defaultValue = "0") Integer page, @PathVariable String username)
    {
        Pageable pageable = PageRequest
                .of(page, 10, Sort.by("titulo").ascending());
        return new ResponseEntity<>(this.taskService.findAllTasks(pageable, username),HttpStatus.OK);
    }
}
