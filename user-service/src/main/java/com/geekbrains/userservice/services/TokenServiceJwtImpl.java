package com.geekbrains.userservice.services;

import com.geekbrains.userservice.entities.User;
import com.geekbrains.userservice.repositories.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Component
@ConfigurationProperties(prefix = "security")
@PropertySource(value = "classpath:application.yml")
@RequiredArgsConstructor
public class TokenServiceJwtImpl implements TokenService {

    private final UserRepository userRepository;

    @Value("${security.token.secret.key}")
    private String secretKey;

    @Value("${security.token.expiration.days}")
    private Long expirationDays;

    @Override
    public String generateToken(Authentication authentication) {

        String login = authentication.getName();

        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User cannot be found."));

        Map<String, Object> claims = new HashMap<>(
                Map.of(
                        "authorities", authorities,
                        "id", user.getId(),
                        "username",user.getUsername(),
                        "email", user.getUserDetails().getEmail()
                )
        );

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(login)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationDays * 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public Claims getClaims(String token) {
        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            //checking mandatory token attributes
            if (claims.getExpiration() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expiration not found in token");
            }
            if (claims.getSubject() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject not found in token");
            }
            if (claims.get("authorities", List.class) == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorities not found in token.");
            }
            if (claims.get("id", Long.class) == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id not found in token");
            }
            if (claims.get("email", String.class) == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found in token.");
            }
            if (claims.get("username", String .class) == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username not found in token");
            }

            return claims;
        } catch (JwtException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something wrong with the token");
        }
    }

    @Override
    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    @Override
    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    @Override
    public boolean hasRight(String token, String right) {
        return getClaims(token).get("authorities", List.class).contains(right);
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).get("username", String.class);
    }

    @Override
    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    @Override
    public String getLogin(String token) {
        return getClaims(token).getSubject();
    }


}
