package com.dahada.backend.application.auth.handler;

import com.dahada.backend.application.auth.dto.Authentication;
import com.dahada.backend.application.auth.service.OAuth2UserPrincipal;
import com.dahada.backend.domain.user.service.OAuth2SignUpService;
import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2SignUpService service;

    public OAuth2AuthenticationSuccessHandler(OAuth2SignUpService service) {
        this.service = service;
    }

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final OAuth2UserPrincipal principal = (OAuth2UserPrincipal) authentication.getUserDetails();
        final OAuth2SignUpUserRequest signUpUserRequest = new OAuth2SignUpUserRequest(
                principal.getName(), principal.getEmail(),
                principal.getProvider(), principal.getAttributes()
        );
        service.signUp(signUpUserRequest);
    }
}
