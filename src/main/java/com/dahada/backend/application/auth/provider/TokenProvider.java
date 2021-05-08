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

    public Map<String, Object> extractPayloadFromToken(String token) {
        return service.extractPayload(token);
    }

    /**
     * @return (token name, token, age)
     */
    public Triple<String, String, Long> issueRefreshToken(Map<String, Object> payload) {
        final JwtProperties.TokenPolicy policy = properties.getTokenPolicy();
        final String refreshTokenName = policy.getRefreshTokenName();
        final long ageOfTokenInSeconds = policy.getRefreshTokenLife().getSeconds();
        final String token = getToken(payload, refreshTokenName, ageOfTokenInSeconds);
        return Triple.of(refreshTokenName, token, ageOfTokenInSeconds);
    }

    /**
     * @return (token name, token, age)
     */
    public Triple<String, String, Long> issueAccessToken(Map<String, Object> payload) {
        final JwtProperties.TokenPolicy policy = properties.getTokenPolicy();
        final String accessTokenName = policy.getAccessTokenName();
        final long ageOfTokenInSeconds = policy.getAccessTokenLife().getSeconds();
        final String token = getToken(payload, accessTokenName, ageOfTokenInSeconds);
        return Triple.of(accessTokenName, token, ageOfTokenInSeconds);
    }

    private String getToken(Map<String, Object> payload, String refreshTokenName, long ageOfTokenInSeconds) {
        final ZonedDateTime now = ZonedDateTime.now();
        final JwtTokenParam param = JwtTokenParam.builder()
                .subject(refreshTokenName)
                .issuer(properties.getIssuer())
                .issuedAt(Date.from(now.toInstant()))
                .expiredAt(Date.from(now.plusSeconds(ageOfTokenInSeconds).toInstant()))
                .payload(payload)
                .build();
        return service.createToken(param);
    }

    public boolean checkTokenValidation(String token) {
        return service.validate(token);
    }
}
