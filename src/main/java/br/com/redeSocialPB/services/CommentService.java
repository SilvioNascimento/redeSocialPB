package br.com.redeSocialPB.services;

import br.com.redeSocialPB.models.Comment;
import br.com.redeSocialPB.repositories.CommentRepository;
import br.com.redeSocialPB.exception.CommentNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getComment(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comentário com a id " +
                id + " não foi encontrado!"));
    }

    public Comment createComment(Comment c) {
        c.setDataCriacao(LocalDate.now());
        c.setHoraCriacao(LocalTime.now());
        return commentRepository.save(c);
    }

    public void deleteComment(String id) {
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if(commentOpt.isPresent()) {
            commentRepository.deleteById(id);
            return;
        }
        throw new CommentNotFoundException("Comentário com id " + id +
                " não foi encontrado para ser deletado!");

    }

    public Comment updateComment(String id, Comment c) {
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if(commentOpt.isPresent()) {
            Comment toUpdate = commentOpt.get();
            toUpdate.setComentario(c.getComentario());
            toUpdate.setDataAtualizacao(LocalDate.now());
            toUpdate.setHoraAtualizacao(LocalTime.now());
            return commentRepository.save(toUpdate);
        }
        throw new CommentNotFoundException("Comentário com id " + id +
                " não foi encontrado para ser atualizado!");
    }
}
