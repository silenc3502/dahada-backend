package com.dahada.backend.application.configuration.props;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {

    private String secret;
    private TokenPolicy tokenPolicy = new TokenPolicy();

    @Getter
    @Setter
    @ToString
    static class TokenPolicy {
        private String accessTokenName;
        private Duration accessTokenLife;
        private String refreshTokenName;
        private Duration refreshTokenLife;
    }
}
