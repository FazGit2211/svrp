package faz.api.svrp.controllers;

import faz.api.svrp.dtos.AuthResponse;
import faz.api.svrp.dtos.DtoLogin;
import faz.api.svrp.dtos.DtoSignin;
import faz.api.svrp.services.authServices.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody DtoLogin dtoLogin) {
        return ResponseEntity.ok(authService.login(dtoLogin));
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody DtoSignin dtoSignin) {
        authService.signIn(dtoSignin);
        return ResponseEntity.ok().build();
    }
}
