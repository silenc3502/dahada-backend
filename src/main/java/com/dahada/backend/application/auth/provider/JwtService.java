package com.dahada.backend.application.auth.provider;

import com.dahada.backend.application.configuration.props.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtService {

    private final JwtParser parser;
    private final SecretKey secretKey;
    private final static Map<String, Object> HEADERS = new HashMap<>();

    static {
        HEADERS.put("typ", "JWT");
    }

    public JwtService(JwtProperties properties) {
        String secret = properties.getSecret();
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String createToken(JwtTokenParam payload) {
        return Jwts.builder()
                .setHeader(HEADERS)
                .setSubject(payload.getSubject())
                .setIssuer(payload.getIssuer())
                .setIssuedAt(payload.getIssuedAt())
                .setExpiration(payload.getExpiredAt())
                .claim("info", payload.getPayload())
                .signWith(secretKey)
                .compact();
    }

    public boolean validate(String token) {
        try {
            parser.parse(token);
            final String payload = token.substring(0, token.lastIndexOf('.') + 1);
            final Date expiration = ((Claims) parser.parse(payload).getBody()).getExpiration();
            return new Date().before(expiration);
        } catch (Throwable t) {
            return false;
        }
    }

    public Map<String, Object> extractPayload(String token) {
        try {
            return parser.parseClaimsJws(token).getBody();
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
            return new HashMap<>();
        }
    }

}
