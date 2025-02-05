package br.com.redeSocialPB.controllers;

import br.com.redeSocialPB.dto.PostDTO;
import br.com.redeSocialPB.models.Post;
import br.com.redeSocialPB.services.PostService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    private final ModelMapper modelMapper;
    private final PostService postService;

    public PostController(ModelMapper modelMapper, PostService postService) {
        this.modelMapper = modelMapper;
        this.postService = postService;
    }
    @GetMapping(value = "/post")
    public List<PostDTO> getPosts() {
        return postService.getPosts().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/post/{postId}")
    public PostDTO getPost(@PathVariable String postId) {
        Post p = postService.getPost(postId);
        return convertToDTO(p);
    }

    @PostMapping(value = "/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        Post p = convertToEntity(postDTO);
        Post saved = postService.createPost(p);
        return convertToDTO(saved);
    }

    @PutMapping(value = "/post/{postId}")
    public PostDTO updatePost(@PathVariable String postId,
                              @RequestBody PostDTO postDTO) {
        Post p = convertToEntity(postDTO);
        Post postUpdated = postService.updatePost(postId, p);
        return convertToDTO(postUpdated);
    }

    @PutMapping(value = "/post/{postId}/comment/{commentId}/addCommentToPost")
    public PostDTO addCommentToPost(@PathVariable String postId, @PathVariable String commentId) {
        return convertToDTO(postService.addCommentToPost(postId, commentId));
    }

    @PutMapping(value = "/post/{postId}/comment/{commentId}/removeCommentToPost")
    public PostDTO removeCommentToPost(@PathVariable String postId, @PathVariable String commentId) {
        return convertToDTO(postService.removeCommentToPost(postId, commentId));
    }

    @DeleteMapping(value = "/post/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
    }

    private PostDTO convertToDTO(Post p) {
        return modelMapper.map(p, PostDTO.class);
    }

    private Post convertToEntity(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }
}
