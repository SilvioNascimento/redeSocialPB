package br.com.redeSocialPB.dto;

import br.com.redeSocialPB.validation.EmailsExistentes;
import jakarta.validation.constraints.*;

public class RegisterRequestDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;

    @Email
    @EmailsExistentes
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
