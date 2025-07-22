package br.com.fiap.gerenciatarefas.adapters.outbound.JPA.repositories;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import br.com.fiap.gerenciatarefas.core.user.port.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryPort {


    @Autowired
    public final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void registerUser(UserJpa user)
    {
        this.jpaUserRepository.save(user);
    }

    @Override
    public UserDetails findByLogin(String login) {

        UserDetails user = this.jpaUserRepository.findByLogin(login);
        return user;
    }
}
