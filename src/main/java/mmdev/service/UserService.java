package mmdev.service;

import mmdev.dto.request.CreateUserRequest;
import mmdev.dto.request.UpdateUserRequest;
import mmdev.dto.response.UserResponse;
import mmdev.entity.User;
import mmdev.exception.ResourceNotFoundException;
import mmdev.mapper.UserMapper;
import mmdev.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request){
        User user = UserMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public UserResponse getUserById(Long id){
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found: " + id));
        return UserMapper.toResponse(user);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found: " + id));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);

    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found " + id);
        }
        userRepository.deleteById(id);
    }
}
