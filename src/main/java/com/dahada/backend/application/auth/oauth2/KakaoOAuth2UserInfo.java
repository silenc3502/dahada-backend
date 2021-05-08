package com.dahada.backend.application.auth.oauth2;

import com.dahada.backend.domain.authentication.Provider;
import lombok.ToString;

import java.util.Map;

@ToString(callSuper = true)
public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    private final Map<String, Object> info;

    @SuppressWarnings("UNCHECKED_CAST")
    public KakaoOAuth2UserInfo(Map<String, Object> attribute) {
        super(Provider.KAKAO, attribute);
        info = (Map<String, Object>) attribute.get("kakao_account");
    }

    @Override
    public String getId() {
        return String.valueOf(attribute.get("id"));
    }

    @Override
    @SuppressWarnings("UNCHECKED_CAST")
    public String getName() {
        final Map<String, Object> profile = (Map<String, Object>) info.get("profile");
        return String.valueOf(profile.get("nickname"));
    }

    @Override
    public String getEmail() {
        return String.valueOf(info.get("email"));
    }

    @Override
    public String getImageUrl() {
        return "";
    }
}
