package com.dahada.backend.application.configuration.props;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "app.oauth2.registration.naver")
public class OAuth2NaverRegistrationProperties {
    private String clientId;
    private String clientSecret;
    @NestedConfigurationProperty
    private List<String> scope;
    private String redirectUri;
    private String authorizationGrantType;
    private String clientAuthenticationMethod;
    private String resourceUrl;

    public String joinedScope() {
        return String.join(",", scope);
    }
}
