package kz.iitu.authservice.controller;

import kz.iitu.authservice.service.AuthService;
import kz.iitu.cfaslib.controller.AbstractWrapper;
import kz.iitu.cfaslib.dto.LoginRequest;
import kz.iitu.cfaslib.dto.UserDto;
import kz.iitu.cfaslib.feign.UserFeign;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractWrapper {

    private final AuthService authService;
    private final UserFeign userFeign;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return responseWrap(authService.login(loginRequest));
    }

    @PostMapping("/validateToken")
    public boolean validateToken(@RequestParam String token) {
        if (StringUtils.isBlank(token)) return false;
        return authService.validateToken(token);
    }

    @PostMapping("/test")
    public UserDto test() {
        return userFeign.byUsername("ceo").getBody();
    }
}
