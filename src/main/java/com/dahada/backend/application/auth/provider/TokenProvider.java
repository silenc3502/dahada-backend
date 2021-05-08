package com.dahada.backend.application.auth.provider;

import com.dahada.backend.application.configuration.props.JwtProperties;
import com.dahada.backend.lang.Triple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class TokenProvider {

    private final JwtService service;
    private final JwtProperties properties;

    public TokenProvider(JwtService service, JwtProperties properties) {
        this.service = service;
        this.properties = properties;
    }

    /**
     * @return (token name, token, age)
     */
    public Triple<String, String, Long> issueRefreshToken(Map<String, Object> data) {
        final String refreshTokenName = properties.getTokenPolicy().getRefreshTokenName();
        final long ageOfTokenInSeconds = properties.getTokenPolicy().getRefreshTokenLife().getSeconds();
        final ZonedDateTime now = ZonedDateTime.now();
        final JwtPayload payload = JwtPayload.builder()
                .subject(refreshTokenName)
                .issuer(properties.getIssuer())
                .issuedAt(Date.from(now.toInstant()))
                .expiredAt(Date.from(now.plusSeconds(ageOfTokenInSeconds).toInstant()))
                .payload(data)
                .build();
        final String token = service.createToken(payload);
        return Triple.of(refreshTokenName, token, ageOfTokenInSeconds);
    }
}
