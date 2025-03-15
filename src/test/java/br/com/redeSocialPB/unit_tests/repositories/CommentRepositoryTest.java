package br.com.redeSocialPB.unit_tests.repositories;

import br.com.redeSocialPB.entities.Comment;
import br.com.redeSocialPB.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    private Comment comment1, comment2;

    @BeforeEach
    void setup() {
        comment1 = new Comment();
        comment2 = new Comment();

        comment1.setComentario("Olá!\nEsse é o primeiro comentário!");
        comment1.setDataCriacao(LocalDateTime.now());

        comment2.setComentario("Olá!\nEsse é o segundo comentário!");
        comment2.setDataCriacao(LocalDateTime.now());

        commentRepository.saveAll(List.of(comment1, comment2));
    }

    @Test
    public void testFindAll() {
        List<Comment> comments = commentRepository.findAll();

        assertEquals(2, comments.size(), "A quantidade salva não é o mesmo que o esperado. " +
                "\nQuantidade atual: " + comments.size() + "\t\tQuantidade esperado: 2");
        assertTrue(comments.contains(comment1), "Comentário " + comment1.getId() + " não foi encontrado.");
        assertTrue(comments.contains(comment2), "Comentário " + comment2.getId() + " não foi encontrado.");
    }
}
