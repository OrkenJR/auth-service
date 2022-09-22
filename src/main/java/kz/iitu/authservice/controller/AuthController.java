package kz.iitu.authservice.controller;

import kz.iitu.authservice.dto.LoginRequest;
import kz.iitu.authservice.dto.LoginResponse;
import kz.iitu.authservice.service.AuthService;
import kz.iitu.authservice.service.Impl.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token) throws Exception {
        return new ResponseEntity<>(authService.validateToken(token), HttpStatus.OK);
    }
}
