package br.com.fiap.gerenciatarefas.infra.security.Exception;

public class RegisterFailedException extends RuntimeException {

    public RegisterFailedException(String message) {
        super(message);
    }

    public RegisterFailedException() {
        super("Erro no registro, usuário já cadastrado");
    }
}
