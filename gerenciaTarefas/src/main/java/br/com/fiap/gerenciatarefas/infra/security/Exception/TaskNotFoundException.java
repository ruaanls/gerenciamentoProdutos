package br.com.fiap.gerenciatarefas.infra.security.Exception;

public class TaskNotFoundException extends RuntimeException
{
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException()
    {
        super("Task não encontrada, por favor informe um ID de task válido");
    }
}
