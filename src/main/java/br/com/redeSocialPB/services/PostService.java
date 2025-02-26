package br.com.redeSocialPB.services;

import br.com.redeSocialPB.exception.CommentNotFoundException;
import br.com.redeSocialPB.exception.PostNotFoundException;
import br.com.redeSocialPB.models.Comment;
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
                .orElseThrow(() -> new PostNotFoundException("Post com id " +
                        id + " não foi encontrado!"));
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
        throw new PostNotFoundException("Post com id " + id +
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
        throw new PostNotFoundException("Post com id " + id +
                " não foi encontrado para ser atualizado!");
    }

    public Post addCommentToPost(String postId, String commentId) {
        Optional<Post> postOpt = postRepository.findByIdWithComments(postId);
        Optional<Comment> commentOpt = commentRepository.findById(commentId);

        if(postOpt.isPresent() && commentOpt.isPresent()) {
            Post p = postOpt.get();
            Comment c = commentOpt.get();

            c.setPost(p);
            p.getComments().add(c);
            commentRepository.save(c);
            return postRepository.save(p);
        }
        else if(postOpt.isEmpty()){
            throw new PostNotFoundException("Post com id " + postId +
                    " não existe!");
        } else {
            throw new CommentNotFoundException("Comentário com id " + commentId +
                    " não existe!");
        }
    }

    public Post removeCommentToPost(String postId, String commentId) {
        Optional<Post> postOpt = postRepository.findByIdWithComments(postId);
        Optional<Comment> commentOpt = commentRepository.findById(commentId);

        if(postOpt.isPresent() && commentOpt.isPresent()) {
            Post p = postOpt.get();
            Comment c = commentOpt.get();

            c.setPost(null);
            p.getComments().remove(c);
            commentRepository.save(c);
            return postRepository.save(p);
        }
        else if(postOpt.isEmpty()){
            throw new PostNotFoundException("Post com id " + postId +
                    " não existe!");
        } else {
            throw new CommentNotFoundException("Comentário com id " + commentId +
                    " não existe!");
        }
    }
}
