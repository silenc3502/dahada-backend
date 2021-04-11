package com.dahada.backend.application.configuration;

import com.dahada.backend.domain.user.enitity.UserRole;
import com.dahada.backend.domain.user.service.UserQueryService;
import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserQueryService queryService;

    public CustomOAuth2UserService(UserQueryService queryService) {
        this.queryService = queryService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final var delegator = new DefaultOAuth2UserService();
        final OAuth2User oAuth2User = delegator.loadUser(userRequest);

        final String registrationId = registrationId(userRequest);
        final String userNameAttribute = userNameAttribute(userRequest);
        final OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttribute, oAuth2User.getAttributes());

        log.debug("oAuth2User: {}", oAuth2User);
        log.debug("userNameAttribute: {}", userNameAttribute);
        log.debug("attributes: {}", attributes);

        final CheckUserExistenceRequest checkExistenceRequest = new CheckUserExistenceRequest(attributes.getEmail());
        if (queryService.exist(checkExistenceRequest)) {

        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(UserRole.DAHADA_USER.name())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private String registrationId(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration().getRegistrationId();
    }

    private String userNameAttribute(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
    }
}
