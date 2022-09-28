package kz.iitu.authservice.repository;

import kz.iitu.authservice.dto.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
    Token findTokenById(String id);
}