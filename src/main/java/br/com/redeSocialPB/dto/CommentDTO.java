package br.com.redeSocialPB.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class CommentDTO {
    private String id;

    @NotBlank(message = "Comentário não pode ser em branco")
    @NotEmpty(message = "Comentário não pode ser vazia")
    @NotNull(message = "Comentário não pode ser nula")
    private String comentario;

    @JsonAlias({"datacriacao", "data_criacao"})
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    @JsonAlias({"dataatualizacao", "data_atualizacao"})
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    @JsonAlias({"horacriacao", "hora_criacao"})
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaCriacao;

    @JsonAlias({"horaatualizacao", "hora_atualizacao"})
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaAtualizacao;

    public CommentDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalTime getHoraCriacao() {
        return horaCriacao;
    }

    public void setHoraCriacao(LocalTime horaCriacao) {
        this.horaCriacao = horaCriacao;
    }

    public LocalTime getHoraAtualizacao() {
        return horaAtualizacao;
    }

    public void setHoraAtualizacao(LocalTime horaAtualizacao) {
        this.horaAtualizacao = horaAtualizacao;
    }
}
