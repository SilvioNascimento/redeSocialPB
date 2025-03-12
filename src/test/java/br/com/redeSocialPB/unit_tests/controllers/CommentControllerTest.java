package br.com.redeSocialPB.unit_tests.controllers;

import br.com.redeSocialPB.dto.CommentDTO;
import br.com.redeSocialPB.exception.CommentNotFoundException;
import br.com.redeSocialPB.models.Comment;
import br.com.redeSocialPB.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CommentService commentService;

    private Comment comment1, comment2, commentCreate, commentUpdate;
    private CommentDTO commentDTO1, commentDTO2, commentDTOCreate, commentDTOUpdate;
    private String id1, id2, id3;

    @BeforeEach
    void setup() {
        id1 = UUID.randomUUID().toString();
        id2 = UUID.randomUUID().toString();
        id3 = UUID.randomUUID().toString();

        comment1 = new Comment();
        comment1.setId(id1);
        comment1.setComentario("Olá! Esse é o 1º comentário teste!");
        comment1.setDataCriacao(LocalDateTime.now());

        comment2 = new Comment();
        comment2.setId(id2);
        comment2.setComentario("Olá! Esse é o 2º comentário teste!");
        comment2.setDataCriacao(LocalDateTime.now());

        commentCreate = new Comment();
        commentCreate.setComentario("Olá! Esse é o 3º comentário teste!");

        commentUpdate = new Comment();
        commentUpdate.setId(id3);
        commentUpdate.setComentario("Olá! Esse é um comentário atualizado!");

        commentDTO1 = new CommentDTO();
        commentDTO1.setId(id1);
        commentDTO1.setComentario("Olá! Esse é o 1º comentário teste!");
        commentDTO1.setDataCriacao(LocalDateTime.now());

        commentDTO2 = new CommentDTO();
        commentDTO2.setId(id2);
        commentDTO2.setComentario("Olá! Esse é o 2º comentário teste!");
        commentDTO2.setDataCriacao(LocalDateTime.now());

        commentDTOCreate = new CommentDTO();
        commentDTOCreate.setComentario("Olá! Esse é o 3º comentário teste!");

        commentDTOUpdate = new CommentDTO();
        commentDTOUpdate.setId(id3);
        commentDTOUpdate.setComentario("Olá! Esse é um comentário atualizado!");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetComments() throws Exception {
        List<Comment> comments = Arrays.asList(comment1, comment2);
        Mockito.when(commentService.getComments()).thenReturn(Arrays.asList(comment1, comment2));

        mockMvc.perform(get("/api/comment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(commentDTO1.getId()))
                .andExpect(jsonPath("$[0].comentario").value(commentDTO1.getComentario()))
                .andExpect(jsonPath("$[0].dataCriacao").value(commentDTO1.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(jsonPath("$[1].id").value(commentDTO2.getId()))
                .andExpect(jsonPath("$[1].comentario").value(commentDTO2.getComentario()))
                .andExpect(jsonPath("$[1].dataCriacao").value(commentDTO2.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetComment() throws Exception {
        Mockito.when(commentService.getComment(comment1.getId())).thenReturn(comment1);

        mockMvc.perform(get("/api/comment/{id}", comment1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentDTO1.getId()))
                .andExpect(jsonPath("$.comentario").value(commentDTO1.getComentario()))
                .andExpect(jsonPath("$.dataCriacao").value(commentDTO1.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetCommentNotFound() throws Exception {
        Mockito.when(commentService.getComment(Mockito.anyString())).thenThrow(new CommentNotFoundException("Comentário não encontrado"));

        mockMvc.perform(get("/api/comment/{id}", UUID.randomUUID().toString()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]").value("Comentário não encontrado"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreateComment() throws Exception {
        Mockito.when(commentService.createComment(Mockito.any(Comment.class))).thenReturn(commentCreate);

        mockMvc.perform(post("/api/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(commentDTOCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(commentCreate.getId()))
                .andExpect(jsonPath("$.comentario").value(commentCreate.getComentario()));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testUpdateComment() throws Exception {
        Mockito.when(commentService.updateComment(Mockito.eq(commentUpdate.getId()), Mockito.any(Comment.class))).thenReturn(commentUpdate);

        mockMvc.perform(put("/api/comment/{id}", commentUpdate.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(commentDTOUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentUpdate.getId()))
                .andExpect(jsonPath("$.comentario").value(commentUpdate.getComentario()));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testUpdateCommentNotFound() throws Exception {
        Mockito.when(commentService.updateComment(Mockito.anyString(), Mockito.any(Comment.class))).thenThrow(new CommentNotFoundException("Comentário não encontrado"));

        mockMvc.perform(put("/api/comment/{id}", UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(commentDTOUpdate))) // Envia o corpo com CommentDTO
                .andExpect(status().isNotFound()) // Espera o status 404
                .andExpect(jsonPath("$.errors[0]").value("Comentário não encontrado"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeleteComment() throws Exception {
        Mockito.doNothing().when(commentService).deleteComment(Mockito.anyString());

        mockMvc.perform(delete("/api/comment/{commentId}", UUID.randomUUID().toString()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeleteCommentNotFound() throws Exception {
        String commentId = UUID.randomUUID().toString();
        Mockito.doThrow(new CommentNotFoundException("Comentário com id " + commentId + " não foi encontrado para ser deletado!"))
                .when(commentService).deleteComment(commentId);

        mockMvc.perform(delete("/api/comment/{commentId}", commentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]").value("Comentário com id " + commentId + " não foi encontrado para ser deletado!"));
    }
}
