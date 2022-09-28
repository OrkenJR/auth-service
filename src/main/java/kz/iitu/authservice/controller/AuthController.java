package kz.iitu.authservice.controller;

import kz.iitu.authservice.auth.CurrentUserHolder;
import kz.iitu.authservice.dto.LoginRequest;
import kz.iitu.authservice.dto.LoginResponse;
import kz.iitu.authservice.dto.Token;
import kz.iitu.authservice.service.AuthService;
import kz.iitu.authservice.service.Impl.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public boolean validateToken(@RequestHeader(value = "Authorization", required = false) String header,
                                     @RequestParam String token) {
        Token currentToken = CurrentUserHolder.currentToken.get();

        System.out.println(currentToken);

        return "qwerty123".equals(token);
        //        return authService.validateToken(token);
    }
}
