package kz.iitu.authservice.config;

import kz.iitu.authservice.dto.User;
import kz.iitu.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Реализация UserDetailsService, обращается в микросервис по пользователям и вытаскивает пользователя по юзернейму
 * Нужна для маппинга объектов, хранящихся в базе данных, на объекты, требуемые Spring Security
 *
 * @author Orken
 * @version 1.0.0
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), buildAuthorities(new HashSet<>()));
    }

    //TODO Роли потом продумаем
    private static List<SimpleGrantedAuthority> buildAuthorities(final Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
