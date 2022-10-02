package kz.iitu.authservice.config.jwt;

import kz.iitu.authservice.auth.CurrentUserHolder;
import kz.iitu.authservice.dto.Token;
import kz.iitu.authservice.exception.AuthException;
import kz.iitu.authservice.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static kz.iitu.authservice.util.StringUtil.isEmpty;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private final TokenRepository tokenRepository;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final List<String> allowed = List.of("/login", "/register");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (!allowed.contains(request.getRequestURI().substring(request.getContextPath().length()))) {

            if (isEmpty(token)) {
                throw new AuthException("ssKvz4iYss :: Please authorise into system to continue");
            }
            Token tokenDto = tokenRepository.findTokenById(token);

            if (tokenDto == null) {
                throw new AuthException("j3vSMqgT0f :: Your session is expired, please login again");
            }

            CurrentUserHolder.currentToken.set(tokenDto);
        }

        filterChain.doFilter(request, response);
    }
}
