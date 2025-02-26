package br.com.redeSocialPB.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post não encontrado!");
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
