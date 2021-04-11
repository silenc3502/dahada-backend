package com.dahada.backend.application.authentication;

import com.dahada.backend.application.authentication.dto.OAuth2Attributes;
import com.dahada.backend.domain.user.service.UserQueryService;
import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Service
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final SessionAuthRepository authRepository;
    private final UserQueryService queryService;

    public OAuth2SuccessHandler(SessionAuthRepository authRepository, UserQueryService queryService) {
        this.authRepository = authRepository;
        this.queryService = queryService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        onAuthenticationSuccess(request, response, authentication);
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final OAuth2Attributes attributes = authRepository.restoreOAuth2Data();
        final String emailToCheck = attributes.getEmail();
        if (queryService.exist(new CheckUserExistenceRequest(emailToCheck))) {

            return;
        }
    }
}
