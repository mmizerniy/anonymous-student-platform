package mmdev.service;


import mmdev.dto.request.CreateCommentRequest;
import mmdev.dto.response.CommentResponse;
import mmdev.entity.Comment;
import mmdev.entity.User;
import mmdev.exception.ResourceNotFoundException;
import mmdev.mapper.CommentMapper;
import mmdev.repository.CommentRepository;
import mmdev.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public CommentResponse createComment(CreateCommentRequest request){
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Author not found"));

        Comment comment = CommentMapper.toEntity(request);

        comment.setAuthor(author);

        Comment savedComment = commentRepository.save(comment);

        return CommentMapper.toResponse(savedComment);
    }

    public CommentResponse getCommentById(Long id){
        Comment comment =  commentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Comment not found with id: " + id));
        return CommentMapper.toResponse(comment);
    }

    public List<CommentResponse> getAllComments(){
        return commentRepository.findAll()
                .stream()
                .map(CommentMapper::toResponse)
                .toList();
    }

    public void deleteComment(Long id){
        if (!commentRepository.existsById(id)){
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
    public List<CommentResponse> getCommentsByMaterial(Long materialId){
        return commentRepository.findByMaterialId(materialId)
                .stream()
                .map(CommentMapper::toResponse)
                .toList();
    }
}
