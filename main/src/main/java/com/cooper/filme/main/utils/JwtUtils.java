package com.cooper.filme.main.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    public static Key getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
