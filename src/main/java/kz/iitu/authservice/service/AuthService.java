package kz.iitu.authservice.service;


import kz.iitu.cfaslib.dto.auth.request.LoginRequest;

public interface AuthService {
    String login(LoginRequest request) throws Exception;

    boolean validateToken(String token);
}
