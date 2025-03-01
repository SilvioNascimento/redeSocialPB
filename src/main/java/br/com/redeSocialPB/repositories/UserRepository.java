package br.com.redeSocialPB.repositories;


import br.com.redeSocialPB.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
