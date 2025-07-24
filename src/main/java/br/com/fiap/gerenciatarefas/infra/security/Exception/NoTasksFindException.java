package br.com.fiap.gerenciatarefas.infra.security.Exception;

public class NoTasksFindException extends RuntimeException {
    public NoTasksFindException(String message) {
        super(message);
    }

  public NoTasksFindException()
  {
    super("Nenhuma tarefa encontrada, por favor crie uma tarefa antes de listar todas");
  }
}
