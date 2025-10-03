package com.nevena.service.impl;

import com.nevena.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.issuer:ticket-reservation}")
    private String issuer;

    @Value("${jwt.audience:ticket-reservation-client}")
    private String audience;

    public String createJwt(User user, Set<String> roles) {
        SecretKey key = buildSigningKey(secret);

        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());
        claims.put("userId", user.getUserId());
        claims.put("name", user.getName());
        claims.put("surname", user.getSurname());
        claims.put("jmbg", user.getJmbg());
        claims.put("roles", roles);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer(issuer)
                .setAudience(audience)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey buildSigningKey(String configuredSecret) {
        byte[] keyBytes = tryDecodeBase64(configuredSecret);
        if (keyBytes == null) {
            keyBytes = configuredSecret.getBytes(StandardCharsets.UTF_8);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private byte[] tryDecodeBase64(String maybeBase64) {
        try {
            return Base64.getDecoder().decode(maybeBase64);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
