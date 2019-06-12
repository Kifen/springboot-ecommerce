package com.gateway.sevice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {


    private final JwtConfig jwtConfig;

    @Autowired
    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt =getJwtFromRequest(httpServletRequest);
            log.info("httpServletRequest.isUserInRole()= {}",httpServletRequest.isUserInRole("USER"));
            if(jwt != null){
                log.info("ABOUT TO VALIDATE TOKEN");
                Claims claims = validateToken(jwt);
                String username = claims.getSubject();
                log.info("USER NAME = {}",username);
                log.info("CLAIMS = {}", claims.toString());
                if(username != null){
                    List<String> authorities = (List<String>) claims.get("authorities");
                    log.info("CLAIM AUTHORITIES = {}", authorities);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    log.info("VALIDATED...");
                }
            }
        }catch (Exception ex){
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Claims validateToken(String authToken) {
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecretKey().getBytes())
                .parseClaimsJws(authToken).getBody();
        log.info("CLAIMS GOTTEN");
        return claims;
    }

    private String getJwtFromRequest(HttpServletRequest httpServletRequest) {
        String bearerToken =httpServletRequest.getHeader(jwtConfig.getHeader());
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            log.info("TOKEN ={}", bearerToken.substring(7,bearerToken.length()));
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
