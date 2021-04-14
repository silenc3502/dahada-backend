package com.dahada.backend.application.auth.interceptor;

import com.dahada.backend.api.rest.dto.OAUth2CallbackParam;
import com.dahada.backend.application.auth.dto.AuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class OAuth2AuthenticationInterceptor extends AbstractAuthenticationInterceptor {

    private final OAuth2AuthenticationService oauth2AuthService;

    public OAuth2AuthenticationInterceptor(OAuth2AuthenticationService oauth2AuthService) {
        this.oauth2AuthService = oauth2AuthService;
    }

    @Override
    AuthenticationToken convert(HttpServletRequest request) {
        log.debug("request uri: {}", request.getRequestURI());
        final OAUth2CallbackParam param = new OAUth2CallbackParam(request);
        log.debug("OAuth2AuthenticationInterceptor#convert: {}", param);
        oauth2AuthService.test(param.toHolder());
        return null;
    }
}
