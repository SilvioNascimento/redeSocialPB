package br.com.redeSocialPB.controllers;

import br.com.redeSocialPB.dto.UserDTO;
import br.com.redeSocialPB.dto.UserResponseDTO;
import br.com.redeSocialPB.entities.User;
import br.com.redeSocialPB.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@Validated
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List<UserResponseDTO> getUsers() {
        return userService.getUsers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/user/{userId}")
    public UserResponseDTO getUser(@PathVariable String userId) {
        User u = userService.getUser(userId);
        return convertToDTO(u);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        User u = convertToEntity(userDTO);
        User saved = userService.createUser(u);
        return convertToDTO(saved);
    }

    @PutMapping(value = "/user/{userId}")
    public UserResponseDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {
        User u = convertToEntity(userDTO);
        User userUpdated = userService.updateUser(userId, u);
        return convertToDTO(userUpdated);
    }

    @PutMapping(value = "/user/{userId}/post/{postId}/addPostToUser")
    public UserResponseDTO addPostToUser(@PathVariable String userId, @PathVariable String postId) {
        return convertToDTO(userService.addPostToUser(userId, postId));
    }

    @PutMapping(value = "/user/{userId}/post/{postId}/removePostToUser")
    public UserResponseDTO removePostToUser(@PathVariable String userId, @PathVariable String postId) {
        return convertToDTO(userService.removePostToUser(userId, postId));
    }

    @DeleteMapping(value = "/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    private UserResponseDTO convertToDTO(User u) {
        return modelMapper.map(u, UserResponseDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
