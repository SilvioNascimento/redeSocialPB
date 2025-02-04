package br.com.redeSocialPB.controllers;

import br.com.redeSocialPB.dto.CommentDTO;
import br.com.redeSocialPB.models.Comment;
import br.com.redeSocialPB.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class CommentController {

    private final ModelMapper modelMapper;
    private final CommentService commentService;

    public CommentController(ModelMapper modelMapper, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }

    @GetMapping(value = "/comment")
    public List<CommentDTO> getComments() {
        return commentService.getComments().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/comment/{commentId}")
    public CommentDTO getComment(@PathVariable String commentId) {
        Comment c = commentService.getComment(commentId);
        return convertToDTO(c);
    }

    @PostMapping(value = "/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
        Comment c = convertToEntity(commentDTO);
        Comment saved = commentService.createComment(c);
        return convertToDTO(saved);
    }

    @PutMapping(value = "/comment/{commentId}")
    public CommentDTO updateComment(@PathVariable String commentId,
                                    @RequestBody CommentDTO commentDTO) {
        Comment c = convertToEntity(commentDTO);
        Comment commentUpdated = commentService.updateComment(commentId, c);
        return convertToDTO(commentUpdated);
    }

    @DeleteMapping(value = "/comment/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
    }

    private CommentDTO convertToDTO(Comment c) {
        return modelMapper.map(c, CommentDTO.class);
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }
}
