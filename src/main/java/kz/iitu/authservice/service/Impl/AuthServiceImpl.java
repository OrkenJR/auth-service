package kz.iitu.authservice.service.Impl;

import kz.iitu.authservice.config.jwt.TokenProvider;
import kz.iitu.authservice.dto.UserDto;
import kz.iitu.authservice.feign.UserFeign;
import kz.iitu.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserFeign userFeign;
    private final TokenProvider tokenProvider;

    //TODO Лень, поэтому буду генерить и давать токен без валидации
    @Override
    public String login(String username, String password) throws Exception {
        UserDto userDto = Optional.ofNullable(userFeign.byUsername(username))
                .filter(response -> response.getStatusCode().is2xxSuccessful())
                .map(HttpEntity::getBody)
                .filter(user -> StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword()))
                .orElseThrow();

        if (!userDto.getUsername().equals(username) && !userDto.getPassword().equals(password)) {
            throw new IllegalAccessException("");
        }
        return tokenProvider.createToken(username);
    }

    @Override
    public Boolean validateToken(String token) throws Exception {
        return tokenProvider.validateToken(token);
    }


}
