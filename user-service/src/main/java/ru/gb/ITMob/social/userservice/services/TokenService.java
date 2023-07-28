package ru.gb.ITMob.social.userservice.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);
    Claims getClaims(String token);

    Long getId(String token);
    String getUsername(String token);
    String getEmail(String token);
    String getLogin(String token);

    boolean isExpired(String token);
    boolean hasRight(String token, String right);

}
