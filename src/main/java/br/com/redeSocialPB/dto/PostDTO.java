package br.com.redeSocialPB.dto;

import br.com.redeSocialPB.entities.Comment;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    private String id;

    @NotBlank(message = "Mensagem não pode ser em branco")
    @NotEmpty(message = "Mensagem não pode ser vazia")
    @NotNull(message = "Mensagem não pode ser nula")
    private String mensagem;

    @JsonAlias({"datacriacao", "data_criacao"})
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonAlias({"dataatualizacao", "data_atualizacao"})
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
