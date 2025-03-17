package br.com.redeSocialPB.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException() {
        super("Usuário não autorizado!");
    }

    public UnauthorizedActionException(String message) {
        super(message);
    }
}
