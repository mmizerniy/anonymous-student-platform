package mmdev.service;


import lombok.SneakyThrows;
import mmdev.dto.request.LoginRequest;
import mmdev.dto.request.RegisterRequest;
import mmdev.dto.response.AuthResponse;
import mmdev.entity.Role;
import mmdev.entity.User;
import mmdev.repository.UserRepository;
import mmdev.security.CustomUserDetails;
import mmdev.security.JwtService;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @SneakyThrows
    public void register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new BadRequestException("Username already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(Role.STUDENT)
                .build();
        userRepository.save(user);
    }
    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(
                request.getUsername()
        ).orElseThrow();
        String token = jwtService.generateToken(
                new CustomUserDetails(user)
        );
        return new AuthResponse(token);
    }
}
