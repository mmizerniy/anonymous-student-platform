package mmdev.controller;


import mmdev.entity.Comment;
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
    public Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
    @GetMapping("/materials/{materialId}")
    public List<Comment> getCommentsByMaterial(@PathVariable Long materialId){
        return commentService.getCommentsByMaterial(materialId);
    }
}
