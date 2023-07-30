package ru.gb.itmob.social.gatewayservice.services;

import io.jsonwebtoken.Claims;

public interface TokenService {

    Claims getClaims(String token);
    Boolean isExpired(String token);
    Long getId(String token);
    String getUsername(String token);
    String getEmail(String token);
    String getLogin(String token);
}
