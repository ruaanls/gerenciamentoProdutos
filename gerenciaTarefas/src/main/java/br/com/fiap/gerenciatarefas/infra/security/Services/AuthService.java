package br.com.fiap.gerenciatarefas.infra.security.Services;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService
{
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails user = jpaUserRepository.findByLogin(username);
        //if(user == null)
        //{

        //}
        return user;

    }
}
