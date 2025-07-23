package br.com.fiap.gerenciatarefas.utils.mapper;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.TasksJPA;
import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskRequestDTO;
import br.com.fiap.gerenciatarefas.core.tasks.DTO.TaskResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TasksMapper
{
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "taskRequestDTO.titulo", target = "titulo"),
            @Mapping(source = "taskRequestDTO.descricao", target = "descricao"),
            @Mapping(source = "taskRequestDTO.prazo", target = "prazo"),
            @Mapping(source = "taskRequestDTO.status", target = "status"),
            @Mapping(source = "user", target = "userId")
    })
    TasksJPA requestToTask(TaskRequestDTO taskRequestDTO, UserJpa user);

    @Mappings({

            @Mapping(source = "tasksJPA.titulo", target = "titulo"),
            @Mapping(source = "tasksJPA.descricao", target = "descricao"),
            @Mapping(source = "tasksJPA.prazo", target = "prazo"),
            @Mapping(source = "tasksJPA.status", target = "status"),
            @Mapping(source = "tasksJPA.userId.login", target = "autor")
    })
    TaskResponseDTO taskToResponse(TasksJPA tasksJPA);


}
