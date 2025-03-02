package br.com.redeSocialPB.security;

import br.com.redeSocialPB.models.User;
import br.com.redeSocialPB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        if(u == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado!");
        }
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getSenha(),
                new ArrayList<>()
        );
    }
}
