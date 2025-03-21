package br.com.redeSocialPB.dto;

import br.com.redeSocialPB.enums.Roles;
import br.com.redeSocialPB.validation.EmailsExistentes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    private String id;

    @NotBlank(message = "Nome não pode ser em branco")
    @NotEmpty(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @Email
    @EmailsExistentes
    private String email;

    @NotBlank(message = "Username não pode ser em branco")
    @NotEmpty(message = "Username não pode ser vazio")
    @NotNull(message = "Username não pode ser nulo")
    private String username;

    @NotBlank(message = "Senha não pode ser em branco")
    @NotEmpty(message = "Senha não pode ser vazia")
    @NotNull(message = "Senha não pode ser nula")
    private String senha;

    private Roles role;

    public UserDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
