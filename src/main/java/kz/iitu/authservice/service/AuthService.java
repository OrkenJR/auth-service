package kz.iitu.authservice.service;

public interface AuthService {
    String login(String username, String password) throws Exception;
}
