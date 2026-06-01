package mmdev.mapper;

import mmdev.dto.request.CreateUserRequest;
import mmdev.dto.response.UserResponse;
import mmdev.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User toEntity(CreateUserRequest request){
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .build();
    }

}
