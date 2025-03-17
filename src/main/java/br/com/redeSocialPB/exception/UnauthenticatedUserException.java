package br.com.redeSocialPB.exception;

public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException() {
        super("Usuário não autenticado!");
    }

    public UnauthenticatedUserException(String message) {
        super(message);
    }
}
