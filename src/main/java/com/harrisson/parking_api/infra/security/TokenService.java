package com.harrisson.parking_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.harrisson.parking_api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.jwt.secret}")
    private String secret;

    @Value("${api.security.jwt.expiration}")
    private Integer expiration;

    private static final String ISSUER = "Parking API";

    public String generateToken(User user) {
            try {
                var algorithm = Algorithm.HMAC256(secret);
                return JWT.create()
                        .withIssuer(ISSUER)
                        .withSubject(user.getLogin())
                        .withExpiresAt(expirationDate())
                        .sign(algorithm);
            } catch (JWTCreationException exception) {
                throw new RuntimeException("Error creating token", exception);
            }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
           throw new RuntimeException("Invalid or expired token", exception);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(expiration).toInstant(ZoneOffset.of("-03:00"));
    }
}
