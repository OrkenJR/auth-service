package kz.iitu.authservice.service.Impl;

import kz.iitu.authservice.dto.LoginRequest;
import kz.iitu.authservice.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Value("${keycloak.login.url}")
    private String loginUrl;
    @Value("${keycloak.client-secret}")
    private String clientSecret;
    @Value("${keycloak.grant-type}")
    private String grantType;
    @Value("${keycloak.client-id}")
    private String clientId;

    private final RestTemplate restTemplate;

    public ResponseEntity<LoginResponse> login(LoginRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", request.getUsername());
        map.add("password", request.getPassword());
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", grantType);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity(loginUrl, httpEntity, LoginResponse.class);

        return ResponseEntity.status(200).body(loginResponse.getBody());

    }
}
