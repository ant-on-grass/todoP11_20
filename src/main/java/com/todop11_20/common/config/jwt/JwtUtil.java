package com.todop11_20.common.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JwtUtil {

  private static final String BEARER_PREFIX = "Bearer ";
  private static final long TOKEN_TIME = 60 * 60 * 1000L;

  @Value("${jwt.secret.key}")
  private String secretKey;
  private Key key;
  private final SignatureAlgorithm signatureAlgorithm =SignatureAlgorithm.HS256;

  @PostConstruct
  public void init() {
    log.info("::: init start :::");
    if (secretKey == null || secretKey.isEmpty()) {
      log.info("::: init fail :::");
      throw new IllegalStateException("JWT Secret Key is not initialized");
    }
    log.info("::: init ing :::");
    byte[] bytes = Base64.getDecoder().decode(secretKey);
    key = Keys.hmacShaKeyFor(bytes);
    log.info("::: init key value : {} :::",key);
    log.info("::: init end :::");
  }

  public String createToken(Long userId, String email) { //, UserRole userRole)
    Date date = new Date();
    log.info("::: key value : {}",key);

    return BEARER_PREFIX +
        Jwts.builder()
            .setSubject(String.valueOf(userId))
            .claim("email",email)
            //.claim("userRole",userRole)
            .setExpiration(new Date(date.getTime()+ TOKEN_TIME))
            .setIssuedAt(date)
            .signWith(key, signatureAlgorithm)
            .compact();
  }

  public String substringToken(HttpServletRequest request) {

    String bearerToken = request.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    throw new RuntimeException();
  }

  public Claims extractClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
