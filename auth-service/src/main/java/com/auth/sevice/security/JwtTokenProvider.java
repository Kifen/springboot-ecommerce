package com.auth.sevice.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${app.jwt.Secret}")
    private String jwtSecret;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date date = new Date();
        Long now = System.currentTimeMillis();

        if(userPrincipal.getAuthorities() == null || userPrincipal.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userPrincipal.getEmail());
        log.info("CLAIMS SUBJECT = {}", claims.getSubject().toString());
        claims.put("authorities", userPrincipal.getAuthorities().stream()
                .map(s->s.toString()).collect(Collectors.toList()));
        claims.put("roleName", userPrincipal.getName());

        log.info("CLAIMS: {}", claims.toString());

        return Jwts.builder().setClaims(claims)
                .setSubject(((UserPrincipal) authentication.getPrincipal()).getEmail())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 72000 * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512,jwtSecret.getBytes())
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
