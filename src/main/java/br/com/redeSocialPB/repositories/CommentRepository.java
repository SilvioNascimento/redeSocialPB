package br.com.redeSocialPB.repositories;

import br.com.redeSocialPB.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
