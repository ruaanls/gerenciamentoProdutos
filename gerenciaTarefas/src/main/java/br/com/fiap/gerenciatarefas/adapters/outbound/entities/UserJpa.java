package br.com.fiap.gerenciatarefas.adapters.outbound.entities;

import br.com.fiap.gerenciatarefas.core.user.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserJpa implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;
    // Salvar no banco de dados a STRING do ENUM
    // Se não adicionar essa annotation o JPA adiciona números no lugar das strings do ENUM por padrão ex: Admin = 1, User = 2
    @Enumerated(EnumType.STRING)
    private UserRole user_role;

    public UserJpa(String id, String login, String password, UserRole user_role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.user_role = user_role;
    }

    public UserJpa(String login, String password, UserRole user_role) {
        this.login = login;
        this.password = password;
        this.user_role = user_role;
    }

    public UserJpa() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.user_role.equals(UserRole.ADMIN))
        {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else
        {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
