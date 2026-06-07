package mmdev.service;


import mmdev.dto.request.CreateCommentRequest;
import mmdev.dto.response.CommentResponse;
import mmdev.entity.Comment;
import mmdev.entity.Material;
import mmdev.entity.User;
import mmdev.exception.ResourceNotFoundException;
import mmdev.mapper.CommentMapper;
import mmdev.repository.CommentRepository;
import mmdev.repository.MaterialRepository;
import mmdev.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MaterialRepository materialRepository;


    public CommentService(CommentRepository commentRepository, UserRepository userRepository, MaterialRepository materialRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.materialRepository = materialRepository;
    }

    public CommentResponse createComment(CreateCommentRequest request, String authorUsername){
        User author = userRepository.findByUsername(authorUsername)
                .orElseThrow(()->
                        new ResourceNotFoundException("Author not found"));
        Material material = materialRepository.findById(request.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));

        Long studentUniversityId = author.getUniversity().getId();
        Long materialUniversityId = material.getSubject().getUniversity().getId();

        if (!studentUniversityId.equals(materialUniversityId)){
            throw new AccessDeniedException("You can left comment only for material your university!");
        }


        Comment comment = CommentMapper.toEntity(request);
        comment.setAuthor(author);
        comment.setMaterial(material);

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
    public void hideComment(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found"));
        comment.setHidden(true);
        commentRepository.save(comment);
    }
}
