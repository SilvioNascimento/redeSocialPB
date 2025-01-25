package br.com.redeSocialPB.dto;

import br.com.redeSocialPB.models.Comment;

import java.util.List;

public class PostDTO {

    private String id;
    private String mensagem;
    private List<Comment> comments;

    public PostDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
