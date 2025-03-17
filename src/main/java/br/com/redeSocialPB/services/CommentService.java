package br.com.redeSocialPB.services;

import br.com.redeSocialPB.entities.Comment;
import br.com.redeSocialPB.entities.User;
import br.com.redeSocialPB.exception.UserNotFoundException;
import br.com.redeSocialPB.repositories.CommentRepository;
import br.com.redeSocialPB.exception.CommentNotFoundException;
import br.com.redeSocialPB.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {       // Organizar testes unitários para este service

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getComment(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comentário com a id " +
                id + " não foi encontrado!"));
    }

    public Comment createComment(Comment c, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UserNotFoundException("Usuário com username " + username +
                    " não foi autenticado!");
        }
        c.setDataCriacao(LocalDateTime.now());

        c.setUser(user);
        user.getComments().add(c);

        userRepository.save(user);
        return commentRepository.save(c);
    }

    public void deleteComment(String id, String username) {
        User user = userRepository.findByUsername(username);
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if(commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            if(!comment.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("O usuário " + username +
                        " não tem permissão para deletar este comentário!");   // Desenvolver exceção personalizada
            }
            commentRepository.deleteById(id);
            return;
        }
        throw new CommentNotFoundException("Comentário com id " + id +
                " não foi encontrado para ser deletado!");
    }

    public Comment updateComment(String id, Comment c, String username) {
        User user = userRepository.findByUsername(username);
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if(commentOpt.isPresent()) {
            Comment toUpdate = commentOpt.get();
            if(!toUpdate.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("O usuário " + username +
                        " não tem permissão para atualizar este comentário!");   // Desenvolver exceção personalizada
            }
            toUpdate.setComentario(c.getComentario());
            toUpdate.setDataAtualizacao(LocalDateTime.now());
            return commentRepository.save(toUpdate);
        }
        throw new CommentNotFoundException("Comentário com id " + id +
                " não foi encontrado para ser atualizado!");
    }
}