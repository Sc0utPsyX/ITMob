package ru.gb.itmob.social.gatewayservice.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "security")
@PropertySource(value = "classpath:application.yml")
public class TokenServiceJwtImpl implements TokenService {

    @Value("${security.token.secret.key}")
    private String secretKey;

//    @Value("${security.token.expiration.days}")
//    private Long expirationDays;

    @Override
    public Claims getClaims(String token) {
        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            String subject = claims.getSubject();
            Long id = claims.get("id", Long.class);
            String email = claims.get("email", String.class);
            String username = claims.get("username", String.class);
            List<?> authorities = claims.get("authorities", List.class);

            //checking mandatory token attributes
            if (expiration == null || "".equals(expiration.toString())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expiration not found in token or empty");
            }
            if (subject == null || "".equals(subject)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject not found in token or empty");
            }
            if (authorities == null || authorities.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorities not found in token or empty");
            }
            if (id == null || id <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id not found in token or invalid");
            }
            if (email == null || "".equals(email)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found in token or empty");
            }
            if (username == null || "".equals(username)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username not found in token or empty");
            }

            return claims;

        } catch (JwtException | IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something wrong with jwt token");
        }
    }

    @Override
    public Boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    @Override
    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
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
