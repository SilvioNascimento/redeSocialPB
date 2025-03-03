package br.com.redeSocialPB.dto;

import br.com.redeSocialPB.enums.Roles;
import br.com.redeSocialPB.models.Comment;
import br.com.redeSocialPB.models.Post;
import br.com.redeSocialPB.validation.EmailsExistentes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public class UserResponseDTO {

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

    private List<Post> posts;

    private List<Comment> comments;

    private Roles role;

    public UserResponseDTO() {
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username);
    }
}
