package faz.api.svrp.controllers;

import faz.api.svrp.dtos.AuthResponse;
import faz.api.svrp.dtos.DtoLogin;
import faz.api.svrp.dtos.DtoSignin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody DtoLogin dtoLogin) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody DtoSignin dtoSignin) {
        return ResponseEntity.ok().build();
    }
}
