package br.com.fiap.gerenciatarefas.infra.security.Exception;

public class OracleInputException extends RuntimeException {
    public OracleInputException(String message) {
        super(message);
    }
}
