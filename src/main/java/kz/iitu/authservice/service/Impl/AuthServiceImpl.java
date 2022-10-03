package kz.iitu.authservice.service.Impl;

import kz.iitu.authservice.dto.User;
import kz.iitu.authservice.repository.UserRepository;
import kz.iitu.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    //TODO Лень, поэтому буду генерить и давать токен без валидации
    @Override
    public String login(String username, String password) throws Exception {
        User user = userRepository.findUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
        if (!user.getUsername().equals(username) && !user.getPassword().equals(password)) {
            throw new IllegalAccessException("");
        }
        return "qwerty";
    }

    @Override
    public Boolean validateToken(String token) {
        return "qwerty".equals(token);
    }


}
