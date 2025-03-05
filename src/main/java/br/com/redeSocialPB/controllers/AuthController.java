package br.com.redeSocialPB.controllers;

import br.com.redeSocialPB.dto.*;
import br.com.redeSocialPB.enums.Roles;
import br.com.redeSocialPB.exception.UserNotFoundException;
import br.com.redeSocialPB.models.User;
import br.com.redeSocialPB.security.JwtUtil;
import br.com.redeSocialPB.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@Validated
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public TokenResponseDTO createAuthenticationToken(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getSenha())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new TokenResponseDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        User user = new User();
        user.setNome(registerRequestDTO.getNome());
        user.setEmail(registerRequestDTO.getEmail());
        user.setUsername(registerRequestDTO.getUsername());
        user.setSenha(registerRequestDTO.getSenha());
        user.setRole(Roles.USER);

        User saved = userService.createUser(user);
        return convertToEntity(saved);
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        User user = userService.getUserByUsername(changePasswordRequestDTO.getUsername());
        if(user == null) {
            throw new UserNotFoundException("Usuário " + changePasswordRequestDTO.getUsername() +
                    " não foi encontrado!");
        }
        user.setSenha(changePasswordRequestDTO.getSenha());
        userService.updateUser(user.getId(), user);
        return "Senha alterada com sucesso!";
    }

    private UserResponseDTO convertToEntity(User u) {
        return modelMapper.map(u, UserResponseDTO.class);
    }
}
