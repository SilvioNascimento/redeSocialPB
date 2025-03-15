package br.com.redeSocialPB.unit_tests.repositories;

import br.com.redeSocialPB.entities.Comment;
import br.com.redeSocialPB.entities.Post;
import br.com.redeSocialPB.repositories.CommentRepository;
import br.com.redeSocialPB.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Post post1, post2;
    private Comment comment;

    @BeforeEach
    void setup() {
        comment = new Comment();
        post1 = new Post();
        post2 = new Post();

        comment.setComentario("Olá!\nEsse é o primeiro comentário!");
        comment.setDataCriacao(LocalDateTime.now());

        post1.setMensagem("Olá!\nEsse é o primeiro post!");
        post1.setDataCriacao(LocalDateTime.now());

        post2.setMensagem("Olá!\nEsse é o segundo post!");
        post2.setDataCriacao(LocalDateTime.now());
        post2.getComments().add(comment);
        comment.setPost(post2);

        commentRepository.save(comment);
        postRepository.saveAll(List.of(post1, post2));
    }

    @Test
    public void testFindByIdWithComments() {
        Optional<Post> postOpt = postRepository.findByIdWithComments(post2.getId());
        Post foundPost = postOpt.get();

        assertNotNull(foundPost, "O objeto é nulo");
        assertEquals(post2, foundPost, "Ambos não são iguais");
    }

    @Test
    public void testFindAllWithComments() {
        List<Post> posts = postRepository.findAllWithComments();

        assertEquals(2, posts.size(), "A quantidade salva não é o mesmo que o esperado.\n" +
                "Quantidade atual: " + posts.size() + "\t\tQuantidade esperado: 1");
        assertTrue(posts.contains(post1), "Post " + post1.getId() + " não foi encontrado.");
        assertTrue(posts.contains(post2), "Post " + post2.getId() + " não foi encontrado.");
    }
}
