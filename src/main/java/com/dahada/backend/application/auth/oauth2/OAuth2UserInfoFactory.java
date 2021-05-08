package com.dahada.backend.application.auth.oauth2;

import com.dahada.backend.application.auth.exception.UnsupportedProviderException;
import com.dahada.backend.domain.authentication.Provider;

import java.util.Map;

public final class OAuth2UserInfoFactory {

    public static OAuth2UserInfo get(Provider provider, Map<String, Object> attributes) {
        switch (provider) {
            case NAVER:
                return new NaverOAuth2UserInfo(attributes);
            case KAKAO:
                return new KakaoOAuth2UserInfo(attributes);
            default:
                throw new UnsupportedProviderException("Not supported yet.");
        }
    }
}
