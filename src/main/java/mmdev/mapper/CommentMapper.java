package mmdev.mapper;

import mmdev.dto.request.CreateCommentRequest;
import mmdev.dto.response.CommentResponse;
import mmdev.entity.Comment;

public class CommentMapper {

    public static CommentResponse toResponse(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorUsername(comment.getAuthor().getUsername())
                .build();
    }
    public static Comment toEntity(CreateCommentRequest request){
        return Comment.builder()
                .text(request.getText())
                .build();
    }


}
