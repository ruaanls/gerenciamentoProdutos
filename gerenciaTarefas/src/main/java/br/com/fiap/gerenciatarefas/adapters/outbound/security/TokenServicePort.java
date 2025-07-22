package br.com.fiap.gerenciatarefas.adapters.outbound.security;

import java.time.Instant;

public interface TokenServicePort
{
    String generateToken(String subject);
    String validateToken(String token);
    Instant genExpirationDate();

}
