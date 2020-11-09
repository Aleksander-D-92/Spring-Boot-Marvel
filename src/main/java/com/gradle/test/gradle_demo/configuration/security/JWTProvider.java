package com.gradle.test.gradle_demo.configuration.security;

import com.gradle.test.gradle_demo.domain.Authority;
import com.gradle.test.gradle_demo.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JWTProvider {
    private final Instant now = Instant.now();
    private final String AUTHORITIES_KEY = "authorities";
    private final String SECRET_KEY = "kuAFqjw/4UzUZjirNWheNa/1CPXg8+odlorNhkR1GO4=";
    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    public String createJWT(User user, boolean rememberME) {
        int days = 1;
        if (rememberME) days = 365;

        String collect = user.getAuthorities().stream()
                .map(Authority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getUserId())
                .claim(AUTHORITIES_KEY, collect)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(days, ChronoUnit.DAYS)))
                .setAudience("you")
                .signWith(key)
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
        if(jwt == null) return null;
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
        } catch (JwtException ex) {
            return null;
        }
        Set<SimpleGrantedAuthority> authorities = Arrays
                .stream(jws.getBody().get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        String subject = jws.getBody().getSubject();
        org.springframework.security.core.userdetails.User principal =
                new org.springframework.security.core.userdetails.User(subject, "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
}
