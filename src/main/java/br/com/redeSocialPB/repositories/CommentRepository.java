package br.com.redeSocialPB.repositories;

import br.com.redeSocialPB.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
