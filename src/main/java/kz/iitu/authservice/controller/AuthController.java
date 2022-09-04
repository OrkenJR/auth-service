package kz.iitu.authservice.controller;

import kz.iitu.authservice.dto.UserDto;
import kz.iitu.authservice.feign.UserFeign;
import kz.iitu.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserFeign userFeign;
    private final AuthService authService;

    @GetMapping("/test")
    public UserDto byUsername(@RequestParam String username){
        return Optional.ofNullable(userFeign.byUsername(username))
                .filter(response -> response.getStatusCode().is2xxSuccessful())
                .map(HttpEntity::getBody)
                .orElseThrow();
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {
        return authService.login(username, password);
    }
}
