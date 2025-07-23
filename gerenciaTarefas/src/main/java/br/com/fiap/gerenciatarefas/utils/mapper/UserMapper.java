package br.com.fiap.gerenciatarefas.utils.mapper;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import br.com.fiap.gerenciatarefas.core.user.DTO.LoginResponseDTO;
import br.com.fiap.gerenciatarefas.core.user.DTO.RegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Optional;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "passwordEncrypted", target = "password"),
            @Mapping(source = "registerDTO.user_role", target = "user_role"),
            @Mapping(source = "registerDTO.login", target = "login")
    })

    UserJpa requestToUser(RegisterDTO registerDTO, String passwordEncrypted);

    @Mappings({
            @Mapping(source = "token", target = "token")
    })
    LoginResponseDTO requestToLogin(String token);

    @Mappings({
            @Mapping(source = "userJpa.id", target = "id"),
            @Mapping(source = "userJpa.login", target = "login"),
            @Mapping(source = "userJpa.password", target = "password"),
            @Mapping(source = "userJpa.user_role", target = "user_role"),
            @Mapping(target = "authorities", ignore = true)
    })
    UserJpa optionalToUser(UserJpa userJpa);
}
