package com.dahada.backend.application.auth.handler;

import com.dahada.backend.application.auth.dto.Authentication;
import com.dahada.backend.application.auth.provider.TokenProvider;
import com.dahada.backend.application.auth.service.OAuth2UserPrincipal;
import com.dahada.backend.domain.user.service.OAuth2SignUpService;
import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;
import com.dahada.backend.lang.Triple;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2SignUpService service;
    private final TokenProvider tokenProvider;

    public OAuth2AuthenticationSuccessHandler(OAuth2SignUpService service, TokenProvider tokenProvider) {
        this.service = service;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final OAuth2UserPrincipal principal = (OAuth2UserPrincipal) authentication.getUserDetails();

        String signature = principal.getSignature();
        if (!principal.isRegisteredUser()) {
            signature = requestSignUp(principal);
        }

        final Map<String, Object> data = principalToMap(principal, signature);
        final Triple<String, String, Long> issuedTokenInfo = tokenProvider.issueRefreshToken(data);
        setCookieForResponse(issuedTokenInfo, response);
        redirect(response);
    }

    private void redirect(HttpServletResponse response) {
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException("TODO: 이거 처리 고쳐라.", e);
        }
    }

    private void setCookieForResponse(Triple<String, String, Long> issuedTokenInfo, HttpServletResponse response) {
        final Cookie cookie = new Cookie(issuedTokenInfo.getFirst(), issuedTokenInfo.getSecond());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Math.toIntExact(issuedTokenInfo.getThird()));
        response.addCookie(cookie);
    }

    private Map<String, Object> principalToMap(OAuth2UserPrincipal principal, String signature) {
        final Map<String, Object> data = new HashMap<>();
        data.put("signature", signature);
        data.put("email", principal.getEmail());
        data.put("name", principal.getName());
        return data;
    }

    private String requestSignUp(OAuth2UserPrincipal principal) {
        final OAuth2SignUpUserRequest signUpUserRequest = new OAuth2SignUpUserRequest(
                principal.getName(), principal.getEmail(),
                principal.getProvider(), principal.getAttributes()
        );
        return service.signUp(signUpUserRequest);
    }
}
