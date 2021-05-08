package com.dahada.backend.application.auth.oauth2;

import com.dahada.backend.domain.authentication.Provider;
import lombok.ToString;

import java.util.Map;

@ToString
public abstract class OAuth2UserInfo {
    protected final Provider provider;
    protected final Map<String, Object> attribute;

    public OAuth2UserInfo(Provider provider, Map<String, Object> attribute) {
        this.provider = provider;
        this.attribute = attribute;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
