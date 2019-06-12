package com.gateway.sevice.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


@Setter
@Getter
public class JwtConfig {


    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.uri:/api/auth/**}")
    private String uri;

    @Value("${security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${security.jwt.expirartion:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secretKey:JWTSuperSecretKey}")
    private String secretKey;
}
