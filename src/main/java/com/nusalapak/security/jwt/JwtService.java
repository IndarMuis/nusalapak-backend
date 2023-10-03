package com.nusalapak.security.jwt;

import com.nusalapak.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date expiredDate =
                Date.from(Instant.now().plusSeconds(SecurityConstants.JWT_EXPIRATION));

        return Jwts.builder()
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "JWT")
                .claim("email", email)
                .setIssuer("nusalapak-service")
                .setExpiration(expiredDate)
                .compact();
    }

    public Claims extractJwtClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid token");
        }
    }

    private Key getSecretKey() {
        byte[] bytes = Decoders.BASE64.decode(SecurityConstants.SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

}
