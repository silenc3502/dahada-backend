package com.dahada.backend.application.auth.provider;

import com.dahada.backend.application.configuration.props.JwtProperties;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private final JwtParser parser;
    private final static Map<String, Object> HEADERS = new HashMap<>();

    static {
        HEADERS.put("typ", "JWT");
    }

    public JwtService(JwtProperties properties) {
        String secret = properties.getSecret();
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String createToken(JwtPayload payload) {
        return Jwts.builder()
                .setHeader(HEADERS)
                .setSubject(payload.getSubject())
                .setIssuer(payload.getIssuer())
                .setIssuedAt(payload.getIssuedAt())
                .setExpiration(payload.getExpiredAt())
                .claim("info", payload.getPayload())
                .compact();
    }

    public boolean validate(String token) {
        try {
            parser.parse(token);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

}
