package kz.iitu.authservice.controller;

import kz.iitu.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {
        return authService.login(username, password);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token) throws Exception {
        return new ResponseEntity<>(authService.validateToken(token), HttpStatus.OK);
    }
}
