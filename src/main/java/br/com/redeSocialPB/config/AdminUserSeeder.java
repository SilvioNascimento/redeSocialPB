package br.com.redeSocialPB.config;

import br.com.redeSocialPB.enums.Roles;
import br.com.redeSocialPB.models.User;
import br.com.redeSocialPB.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserSeeder {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AdminUserSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seedAdminUser() {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setNome("Administrador");
            admin.setUsername("admin");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRole(Roles.ADMIN);
            userRepository.save(admin);
        }
    }
}
