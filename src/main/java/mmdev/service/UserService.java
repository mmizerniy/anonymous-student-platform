package mmdev.service;

import mmdev.entity.User;
import mmdev.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found: " + id));
    }

    public User updateUser(Long id,User user){
        User oldUser = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found: " + id));
            oldUser.setEmail(user.getEmail());
            oldUser.setUsername(user.getUsername());
        return userRepository.save(oldUser);
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("User not found " + id);
        }
        userRepository.deleteById(id);
    }
}
