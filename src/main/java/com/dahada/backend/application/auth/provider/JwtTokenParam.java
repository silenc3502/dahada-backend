package com.dahada.backend.application.auth.provider;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@ToString
public class JwtTokenParam {
    private final String subject;
    private final String issuer;
    private final Date issuedAt;
    private final Date expiredAt;
    private final Map<String, Object> payload;

    @Builder
    public JwtTokenParam(String subject, String issuer, Date issuedAt, Date expiredAt, Map<String, Object> payload) {
        this.subject = subject;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
        this.payload = Optional.ofNullable(payload).orElse(new HashMap<>());
    }
}
