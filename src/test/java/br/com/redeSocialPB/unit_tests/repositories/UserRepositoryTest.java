package br.com.redeSocialPB.unit_tests.repositories;

import br.com.redeSocialPB.enums.Roles;
import br.com.redeSocialPB.entities.User;
import br.com.redeSocialPB.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user1, user2;

    @BeforeEach
    void setup() {
        user1 = new User();
        user1.setNome("Silvio");
        user1.setEmail("silvio@gmail.com");
        user1.setUsername("xtreme_noob");
        user1.setSenha(passwordEncoder.encode("silvio123"));
        user1.setRole(Roles.ADMIN);

        user2 = new User();
        user2.setNome("Maria Cecília");
        user2.setEmail("maria@gmail.com");
        user2.setUsername("maria_dev");
        user2.setSenha(passwordEncoder.encode("maria456"));
        user2.setRole(Roles.USER);

        userRepository.saveAll(List.of(user1, user2));
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();

        assertEquals(2, users.size(), "A quantidade salva não é o mesmo que o esperado. " +
                "\nQuantidade atual: " + users.size() + "\t\tQuantidade esperada: 2");
        assertTrue(users.contains(user1), "Usuário " + user1.getNome() + " não foi encontrado.");
        assertTrue(users.contains(user2), "Usuário " + user2.getNome() + " não foi encontrado.");
    }

    @Test
    public void testFindByUsername() {
        User foundUser = userRepository.findByUsername("maria_dev");

        assertNotNull(foundUser, "O objeto é nulo");
        assertEquals(user2, foundUser, "Ambos não são iguais");
        assertThat(passwordEncoder.matches("maria456", foundUser.getSenha())).isTrue();
    }
}