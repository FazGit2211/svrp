package faz.api.svrp.services.authServices;

import faz.api.svrp.dtos.AuthResponse;
import faz.api.svrp.dtos.DtoLogin;
import faz.api.svrp.dtos.DtoSignin;
import faz.api.svrp.models.User;
import faz.api.svrp.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository repository){
        this.userRepository = repository;
    }

    public AuthResponse login(DtoLogin request) {
        return null;
    }

    public AuthResponse signIn(DtoSignin request) {
        User user = new User(request.getUsername(),request.getPassword(),request.getName(),request.getSurname(),request.getBirthday(),request.getEmail());
        userRepository.save(user);
        return null;
    }
}
