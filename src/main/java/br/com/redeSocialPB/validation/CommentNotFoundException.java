package br.com.redeSocialPB.validation;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
        super("Comentário não encontrado!");
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
