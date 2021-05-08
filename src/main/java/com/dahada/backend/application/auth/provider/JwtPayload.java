package com.dahada.backend.application.auth.provider;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

@Getter
@ToString
public class JwtPayload {
    private String subject;
    private String issuer;
    private Date issuedAt;
    private Date expiredAt;
    private Map<String, Object> payload;

    @Builder
    public JwtPayload(String subject, String issuer, Date issuedAt, Date expiredAt, Map<String, Object> payload) {
        this.subject = subject;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
        this.payload = payload;
    }
}
