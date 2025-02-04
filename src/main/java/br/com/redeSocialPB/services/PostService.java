package br.com.redeSocialPB.services;

import br.com.redeSocialPB.models.Post;
import br.com.redeSocialPB.repositories.CommentRepository;
import br.com.redeSocialPB.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAllWithComments();
    }

    public Post getPost(String id) {
        return postRepository.findByIdWithComments(id)
                .orElseThrow(() -> new RuntimeException("Post não encontrado!"));
    }

    public Post createPost(Post p) {
        return postRepository.save(p);
    }

    public void deletePost(String id) {
        Optional<Post> postOpt = postRepository.findById(id);
        if(postOpt.isPresent()) {
            postRepository.deleteById(id);
            return;
        }
        throw new RuntimeException("Post com id " + id +
                " não foi encontrado para ser deletado!");
    }

    public Post updatePost(String id, Post p) {
        Optional<Post> postOpt = postRepository.findByIdWithComments(id);
        if (postOpt.isPresent()) {
            Post toUpdate = postOpt.get();

            toUpdate.setMensagem(p.getMensagem());
            toUpdate.setUser(p.getUser());

            if (p.getComments() != null) {
                toUpdate.setComments(p.getComments());
            }

            return postRepository.save(toUpdate);
        }
        throw new RuntimeException("Post com id " + id +
                " não foi encontrado para ser atualizado!");
    }
}
