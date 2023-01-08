package kz.iitu.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import kz.iitu.authservice.service.AuthService;
import kz.iitu.cfaslib.controller.AbstractWrapper;
import kz.iitu.cfaslib.dto.auth.request.LoginRequest;
import kz.iitu.cfaslib.dto.auth.request.SecuredLoginRequest;
import kz.iitu.cfaslib.dto.user.UserDto;
import kz.iitu.cfaslib.feign.UserFeign;
import kz.iitu.cfaslib.util.CurrentUserHolder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static kz.iitu.cfaslib.util.SecureUtil.decode;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractWrapper {

    private final AuthService authService;
    private final UserFeign userFeign;

    @Operation(summary = "Login with decoded creds")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SecuredLoginRequest securedLoginRequest) throws Exception {
        String username = decode(securedLoginRequest.username);
        String password = decode(securedLoginRequest.password);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        return responseWrap(authService.login(loginRequest));
    }

    @Operation(summary = "Login with plain username and password, for test purposes only")
    @PostMapping("/login/test")
    public ResponseEntity<String> unsafeLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        return responseWrap(authService.login(loginRequest));
    }

    @Operation(summary = "Validates token, but soon will be deprecated and replaced by spring security")
    @PostMapping("/validateToken")
    public boolean validateToken(@RequestParam String token) {
        if (StringUtils.isBlank(token)) return false;
        return authService.validateToken(token);
    }

    @Operation(summary = "Getting current user, but it is a shit code: see cfas-lib TokenFilter :)")
    @PostMapping("/test")
    public UserDto test() {
        String username = CurrentUserHolder.currentUser.get();
        return userFeign.byUsername("ceo").getBody();
    }
}
