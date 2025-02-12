package br.com.redeSocialPB.controllers;

import br.com.redeSocialPB.dto.UserDTO;
import br.com.redeSocialPB.models.User;
import br.com.redeSocialPB.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/user/{userId}")
    public UserDTO getUser(@PathVariable String userId) {
        User u = userService.getUser(userId);
        return convertToDTO(u);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User u = convertToEntity(userDTO);
        User saved = userService.createUser(u);
        return convertToDTO(saved);
    }

    @PutMapping(value = "/user/{userId}")
    public UserDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {
        User u = convertToEntity(userDTO);
        User userUpdated = userService.updateUser(userId, u);
        return convertToDTO(userUpdated);
    }

    @PutMapping(value = "/user/{userId}/post/{postId}/addPostToUser")
    public UserDTO addPostToUser(@PathVariable String userId, @PathVariable String postId) {
        return convertToDTO(userService.addPostToUser(userId, postId));
    }

    @PutMapping(value = "/user/{userId}/post/{postId}/removePostToUser")
    public UserDTO removePostToUser(@PathVariable String userId, @PathVariable String postId) {
        return convertToDTO(userService.removePostToUser(userId, postId));
    }

    @DeleteMapping(value = "/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    private UserDTO convertToDTO(User u) {
        return modelMapper.map(u, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
