package br.com.fiap.gerenciatarefas.adapters.outbound.security;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;

import java.time.Instant;

public interface TokenServicePort
{
    String generateToken(UserJpa userJpa);
    String validateToken(String token);
    Instant genExpirationDate();

}
