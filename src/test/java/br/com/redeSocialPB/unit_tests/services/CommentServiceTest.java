package br.com.redeSocialPB.unit_tests.services;

import br.com.redeSocialPB.exception.CommentNotFoundException;
import br.com.redeSocialPB.entities.Comment;
import br.com.redeSocialPB.repositories.CommentRepository;
import br.com.redeSocialPB.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;

    @BeforeEach
    void setup() {
        comment = new Comment();
        comment.setComentario("Olá pessoal.");
        comment.setDataCriacao(LocalDateTime.now());
    }

    @Test
    void testGetComments() {
        when(commentRepository.findAll()).thenReturn(Arrays.asList(comment));

        assertEquals(1, commentService.getComments().size(),
                "A quantidade retornada não é o mesmo que o esperado.\n" +
                        "Quantidade atual: " + commentService.getComments().size()
        + "\t\tQuantidade esperada: 1");
    }

    @Test
    void testGetComment() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        assertEquals(comment, commentService.getComment(comment.getId()),
                "Comentário " + comment.getId() + " não foi encontrado.");
    }

    @Test
    void testCommentNotFoundExceptionInGetComment() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () -> commentService.getComment(comment.getId()),
                "Deveria lançar a exceção \"CommentNotFoundException\".");
    }

    @Test
    void testCreateComment() {
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment createComment = commentService.createComment(comment);
        assertNotNull(createComment, "O objeto é nulo.");
        assertEquals(comment.getId(), createComment.getId(),
                "O id atual (" + createComment.getId() +")" +
                        " não é o mesmo que o esperado ("+ comment.getId() +")");
    }

    @Test
    void testUpdateComment() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        comment.setComentario("Olá! Esse é um comentário atualizado!");
        Comment updatedComment = commentService.updateComment(comment.getId(), comment);

        verify(commentRepository, times(1)).save(updatedComment);
    }

    @Test
    void testCommentNotFoundExceptionInUpdateComment() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.empty());

        Comment commentToUpdate = new Comment();
        commentToUpdate.setComentario("Olá! Esse é um comentário teste!");

        assertThrows(CommentNotFoundException.class, () -> {
            commentService.updateComment(comment.getId(), commentToUpdate);
        }, "Deveria lançar a exceção \"CommentNotFoundException\".");
        verify(commentRepository, times(1)).findById(comment.getId());
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void testDeleteComment() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
        doNothing().when(commentRepository).deleteById(comment.getId());

        commentService.deleteComment(comment.getId());

        verify(commentRepository, times(1)).deleteById(comment.getId());
    }

    @Test
    void testCommentNotFoundExceptionInDeleteComment() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () -> {
            commentService.deleteComment(comment.getId());
        }, "Deveria lançar a exceção \"CommentNotFoundException\".");
        verify(commentRepository, times(1)).findById(comment.getId());
        verify(commentRepository, never()).deleteById(comment.getId());
    }
}
