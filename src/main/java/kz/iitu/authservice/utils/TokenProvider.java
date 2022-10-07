package kz.iitu.authservice.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private static final String BEARER_PREFIX = "Bearer";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final String key = Base64.getEncoder().encodeToString("key".getBytes(StandardCharsets.UTF_8));

    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(key)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(String authHeader) {
        return (!Objects.isNull(authHeader) && authHeader.startsWith(BEARER_PREFIX)) ?
                authHeader.substring(7) : null;
    }

}