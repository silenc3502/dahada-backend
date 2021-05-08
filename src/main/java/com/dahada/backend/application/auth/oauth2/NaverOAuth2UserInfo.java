package com.dahada.backend.application.auth.oauth2;

import com.dahada.backend.domain.authentication.Provider;
import lombok.ToString;

import java.util.Map;

@ToString(callSuper = true)
public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    private final Map<String, Object> info;

    @SuppressWarnings("UNCHECKED_CAST")
    public NaverOAuth2UserInfo(Map<String, Object> attribute) {
        super(Provider.NAVER, attribute);
        info = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getId() {
        return String.valueOf(info.get("id"));
    }

    @Override
    public String getName() {
        return String.valueOf(info.get("name"));
    }

    @Override
    public String getEmail() {
        return String.valueOf(info.get("email"));
    }

    @Override
    public String getImageUrl() {
        return String.valueOf(info.get("profile_image"));
    }
}
