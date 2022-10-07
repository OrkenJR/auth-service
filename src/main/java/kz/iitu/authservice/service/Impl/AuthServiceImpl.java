package kz.iitu.authservice.service.Impl;

import kz.iitu.authservice.dto.Token;
import kz.iitu.authservice.repository.TokenRepository;
import kz.iitu.authservice.service.AuthService;
import kz.iitu.authservice.utils.TokenProvider;
import kz.iitu.cfaslib.dto.LoginRequest;
import kz.iitu.cfaslib.dto.UserDto;
import kz.iitu.cfaslib.feign.UserFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserFeign userFeign;
    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;

    @Override
    public String login(LoginRequest loginRequest) throws Exception {
        UserDto user = Optional.ofNullable(userFeign.byUsername(loginRequest.getUsername()))
                .map(HttpEntity::getBody)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", loginRequest.getUsername())));
        if (!user.getUsername().equals(loginRequest.getUsername()) && !user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalAccessException("");
        }

        return tokenRepository.save(Token.builder()
                .id(tokenProvider.createToken(user.getUsername()))
                .dateTime(LocalDateTime.now())
                .userId(user.getId())
                .build()).id;
    }

    @Override
    public boolean validateToken(String token) {
        Token t = tokenRepository.findTokenById(token);
        boolean res = t != null && t.dateTime != null && t.dateTime.isBefore(LocalDateTime.now());
        if (res) {
            t.setDateTime(LocalDateTime.now());
            tokenRepository.saveAndFlush(t);
        }
        return res;
    }

}
