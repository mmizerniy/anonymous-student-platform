package mmdev.controller;

import mmdev.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {

    private final CommentService commentService;

    public ModeratorController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/comments/{id}/hide")
    public void hideComment(@PathVariable Long id){
        commentService.hideComment(id);
    }

}
