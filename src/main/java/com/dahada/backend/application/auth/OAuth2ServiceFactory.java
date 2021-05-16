package com.dahada.backend.application.auth;

import com.dahada.backend.application.configuration.props.OAuth2KakaoRegistrationProperties;
import com.dahada.backend.application.configuration.props.OAuth2NaverRegistrationProperties;
import com.dahada.backend.domain.authentication.Provider;
import com.github.scribejava.apis.KakaoApi;
import com.github.scribejava.apis.NaverApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
public enum OAuth2ServiceFactory {
    NAVER("GET"),
    KAKAO("POST");

    private final String method;
    private OAuth20Service service;
    private String resourceUrl;
    private Verb verb;

    OAuth2ServiceFactory(String method) {
        this.method = method;
    }

    public static OAuth2ServiceFactory getServiceHolder(Provider provider) {
        try {
            return OAuth2ServiceFactory.valueOf(provider.name().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class OAuth2ServiceInjector {
        private final OAuth2NaverRegistrationProperties naverProperty;
        private final OAuth2KakaoRegistrationProperties kakaoProperty;

        @PostConstruct
        public void init() {
            // 네이버
            NAVER.service = new ServiceBuilder(naverProperty.getClientId())
                    .apiSecret(naverProperty.getClientSecret())
                    .defaultScope(naverProperty.joinedScope())
                    .callback(naverProperty.getRedirectUri())
                    .build(NaverApi.instance());
            NAVER.verb = Verb.valueOf(naverProperty.getClientAuthenticationMethod());
            NAVER.resourceUrl = naverProperty.getResourceUrl();

            // 카카오
            KAKAO.service = new ServiceBuilder(kakaoProperty.getClientId())
                    .apiSecret(kakaoProperty.getClientSecret())
                    .defaultScope(kakaoProperty.joinedScope())
                    .callback(kakaoProperty.getRedirectUri())
                    .build(KakaoApi.instance());
            KAKAO.verb = Verb.valueOf(kakaoProperty.getClientAuthenticationMethod());
            KAKAO.resourceUrl = kakaoProperty.getResourceUrl();
        }
    }
}
