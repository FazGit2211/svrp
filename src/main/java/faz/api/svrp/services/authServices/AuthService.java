package faz.api.svrp.services.authServices;

import faz.api.svrp.dtos.AuthResponse;
import faz.api.svrp.dtos.DtoLogin;
import faz.api.svrp.dtos.DtoSignin;
import faz.api.svrp.exceptions.ResourceNotFoundException;
import faz.api.svrp.models.Role;
import faz.api.svrp.models.User;
import faz.api.svrp.repositorys.UserRepository;
import faz.api.svrp.services.jwtServices.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoderParam, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoderParam;
        this.authenticationManager = authManager;
    }

    public AuthResponse login(DtoLogin request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        Optional<User> userExist = userRepository.findByUsername(request.getUsername());
        if (userExist.isEmpty()) {
            throw new ResourceNotFoundException("User not exist");
        }
        String token = jwtService.getToken(userExist.get());
        return new AuthResponse(token);
    }

    public void signIn(DtoSignin request) {
        User user = new User(request.getUsername(), request.getName(), request.getSurname(), request.getBirthday(), request.getEmail());
        user.setPassword(passwordEncoder.encode((request.getPassword())));
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
