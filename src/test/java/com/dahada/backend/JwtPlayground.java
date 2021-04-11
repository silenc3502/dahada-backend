package com.dahada.backend;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@DisplayName("JWT 라이브러리 테스트")
public class JwtPlayground {

    @DisplayName("issue 토큰")
    @Test
    void testIssueToken() throws Exception {

        final Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");

        final KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(256);
        final Key secretKey = gen.generateKey();
        System.out.println(new String(secretKey.getEncoded()));
        final Key hmac = Keys.hmacShaKeyFor("106638089987144484012610094224458723412934116993569226140889172987171544963113".getBytes(StandardCharsets.UTF_8));

        final LocalDateTime now = LocalDateTime.now();
        final String encrypted = Jwts.builder()
                .setHeader(headers)
                .setIssuer("dahada")
                .setSubject("unique_id_here")
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(now.plusDays(1).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(hmac, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("encrypted = " + encrypted);
    }

    @DisplayName("jwt validation")
    @Test
    void testCheckValidation() {
        final String jwtString = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkYWhhZGEiLCJzdWIiOiJ1bmlxdWVfaWRfaGVyZSIsImlhdCI6MTYxODE1MzkwMywiZXhwIjoxNjE4MjQwMzAzfQ.OaZlZIieoYytDM9f5y0dntciK2g3eEw5iFZEXyOj5l0";
        final Key hmac = Keys.hmacShaKeyFor("106638089987144484012610094224458723412934116993569226140889172987171544963113".getBytes(StandardCharsets.UTF_8));
        final JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(hmac)
                .build();

        final Jws<Claims> claimsJws = parser.parseClaimsJws(jwtString);
    }
}
