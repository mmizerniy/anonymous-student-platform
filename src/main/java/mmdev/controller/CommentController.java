package mmdev.controller;


import jakarta.validation.Valid;
import mmdev.dto.request.CreateCommentRequest;
import mmdev.dto.response.CommentResponse;
import mmdev.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(@Valid @RequestBody CreateCommentRequest request){
        return commentService.createComment(request);
    }

    @GetMapping("/{id}")
    public CommentResponse getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
    @GetMapping("/materials/{materialId}")
    public List<CommentResponse> getCommentsByMaterial(@PathVariable Long materialId){
        return commentService.getCommentsByMaterial(materialId);
    }
}
