package mmdev.service;


import mmdev.entity.Comment;
import mmdev.exception.ResourceNotFoundException;
import mmdev.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment){
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Comment not found with id: " + id));
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void deleteComment(Long id){
        if (!commentRepository.existsById(id)){
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
    public List<Comment> getCommentsByMaterial(Long materialId){
        return commentRepository.findByMaterialId(materialId);
    }
}
