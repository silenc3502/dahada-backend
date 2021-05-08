package com.dahada.backend.application.auth.service;

import com.dahada.backend.application.auth.oauth2.OAuth2UserInfo;
import com.dahada.backend.domain.authentication.OAuth2Authentication;
import com.dahada.backend.domain.authentication.Provider;
import com.dahada.backend.domain.user.enitity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class OAuth2UserPrincipal implements UserDetails {
    private final String signature;
    private final Provider provider;
    private final String email;
    private final String name;
    private final boolean isRegisteredUser;
    private final Map<String, Object> attributes;

    public static OAuth2UserPrincipal ofRegisteredUser(User user) {
        final OAuth2Authentication oauth2Info = user.getOauth2Authentication();
        return new OAuth2UserPrincipal(
                user.getSignature(), oauth2Info.getProvider(),
                user.getEmail().getEmail(), user.getName(),
                true, oauth2Info.getAttributes()
        );
    }

    public static OAuth2UserPrincipal ofUnregisteredUser(OAuth2UserInfo oAuth2UserInfo) {
        return new OAuth2UserPrincipal(
                "not-determined-yet", oAuth2UserInfo.getProvider(),
                oAuth2UserInfo.getEmail(), oAuth2UserInfo.getName(),
                false, oAuth2UserInfo.getAttribute()
        );
    }
}
