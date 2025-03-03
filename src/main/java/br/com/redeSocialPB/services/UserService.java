package br.com.redeSocialPB.services;

import br.com.redeSocialPB.enums.Roles;
import br.com.redeSocialPB.exception.UserWithUsernameAlreadyExistsException;
import br.com.redeSocialPB.exception.UserNotFoundException;
import br.com.redeSocialPB.models.Post;
import br.com.redeSocialPB.models.User;
import br.com.redeSocialPB.repositories.PostRepository;
import br.com.redeSocialPB.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PostRepository postRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com id " +
                        id + " não foi encontrado!"));
    }

    public User createUser(User u) {
        if(userRepository.findByUsername(u.getUsername()) != null) {
            throw new UserWithUsernameAlreadyExistsException("Usuário com username " + u.getUsername()
            + " já existe!");
        }
        u.setSenha(bCryptPasswordEncoder.encode(u.getSenha()));
        u.setRole(Roles.USER);
        return userRepository.save(u);
    }

    public void deleteUser(String id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()) {
            userRepository.deleteById(id);
            return;
        }
        throw new UserNotFoundException("User com id " + id +
                " não foi encontrado para ser deletado!");
    }

    public User updateUser(String id, User u) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()) {
            User toUpdate = userOpt.get();

            toUpdate.setNome(u.getNome());
            toUpdate.setEmail(u.getEmail());
            toUpdate.setUsername(u.getUsername());
            toUpdate.setRole(u.getRole());

            if (!u.getSenha().equals(toUpdate.getSenha())) {
                toUpdate.setSenha(bCryptPasswordEncoder.encode(u.getSenha()));
            }

            if(u.getPosts() != null){
                toUpdate.setPosts(u.getPosts());
            }

            if(u.getComments() != null){
                toUpdate.setComments(u.getComments());
            }

            return userRepository.save(toUpdate);
        }
        throw new UserNotFoundException("User com id " + id +
                " não foi encontrado para ser atualizado!");
    }

    public User addPostToUser(String userId, String postId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Post> postOpt = postRepository.findById(postId);

        if(userOpt.isPresent() && postOpt.isPresent()) {
            User u = userOpt.get();
            Post p = postOpt.get();

            u.addPost(p);
            postRepository.save(p);
            return userRepository.save(u);
        }
        return null;    // Lembrar de desenvolver as exceções
    }

    public User removePostToUser(String userId, String postId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Post> postOpt = postRepository.findById(postId);

        if(userOpt.isPresent() && postOpt.isPresent()) {
            User u = userOpt.get();
            Post p = postOpt.get();

            u.removePost(p);
            postRepository.save(p);
            return userRepository.save(u);
        }
        return null;    // Lembrar de desenvolver as exceções
    }
}
