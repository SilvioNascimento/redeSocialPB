package br.com.redeSocialPB.repositories;

import br.com.redeSocialPB.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
