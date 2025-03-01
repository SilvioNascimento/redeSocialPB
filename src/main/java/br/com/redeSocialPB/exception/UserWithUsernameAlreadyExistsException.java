package br.com.redeSocialPB.exception;

public class UserWithUsernameAlreadyExistsException extends RuntimeException {
    public UserWithUsernameAlreadyExistsException() {
        super("Este usuário já existe!");
    }

    public UserWithUsernameAlreadyExistsException(String message) {
        super(message);
    }
}
