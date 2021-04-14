package com.dahada.backend.application.auth;

import com.dahada.backend.application.configuration.props.OAuth2KakaoRegistrationProperties;
import com.dahada.backend.application.configuration.props.OAuth2NaverRegistrationProperties;
import com.github.scribejava.apis.KakaoApi;
import com.github.scribejava.apis.NaverApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
public enum OAuth2ServiceFactory {
    NAVER("https://openapi.naver.com/v1/nid/me", "GET"),
    KAKAO("https://kapi.kakao.com/v2/user/me", "POST");

    private final String resourceUrl;
    private final String method;
    private OAuth20Service service;

    OAuth2ServiceFactory(String resourceUrl, String method) {
        this.resourceUrl = resourceUrl;
        this.method = method;
    }

    @RequiredArgsConstructor
    @Component
    public static class OAuth2ServiceInjector {
        private final OAuth2NaverRegistrationProperties naverProperty;
        private final OAuth2KakaoRegistrationProperties kakaoProperty;

        @PostConstruct
        public void init() {
            NAVER.service = new ServiceBuilder(naverProperty.getClientId())
                    .apiSecret(naverProperty.getClientSecret())
                    .defaultScope(naverProperty.joinedScope())
                    .callback(naverProperty.getRedirectUri())
                    .build(NaverApi.instance());

            KAKAO.service = new ServiceBuilder(kakaoProperty.getClientId())
                    .apiSecret(kakaoProperty.getClientSecret())
                    .defaultScope(kakaoProperty.joinedScope())
                    .callback(kakaoProperty.getRedirectUri())
                    .build(KakaoApi.instance());
        }
    }
}
