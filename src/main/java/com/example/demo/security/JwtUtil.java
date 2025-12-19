package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret;
    private final long validityInMs;

    public JwtUtil(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    // Generate a JWT token
    public String generateToken(String subject, Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Validate a JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract the email from the JWT token
    public String getEmail(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Extract userId from the JWT token
    public Long getUserId(String token) {
        return getClaimFromToken(token, claims -> claims.get("userId", Long.class));
    }

    // Extract role from the JWT token
    public String getRole(String token) {
        return getClaimFromToken(token, claims -> claims.get("role", String.class));
    }

    // Helper method to extract claims
    private <T> T getClaimFromToken(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.resolve(claims);
    }

    // Extract claims from token
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Functional interface for claims resolution
    @FunctionalInterface
    public interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }
}
