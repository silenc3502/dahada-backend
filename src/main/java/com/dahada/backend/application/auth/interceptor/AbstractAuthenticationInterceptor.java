package com.dahada.backend.application.auth.interceptor;

import com.dahada.backend.application.auth.SecurityContext;
import com.dahada.backend.application.auth.SecurityContextHolder;
import com.dahada.backend.application.auth.dto.Authentication;
import com.dahada.backend.application.auth.dto.AuthenticationToken;
import com.dahada.backend.application.auth.exception.AuthenticationException;
import com.dahada.backend.application.auth.handler.AuthenticationFailureHandler;
import com.dahada.backend.application.auth.handler.AuthenticationSuccessHandler;
import com.dahada.backend.application.auth.provider.Authenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nobody
 */
@Slf4j
abstract class AbstractAuthenticationInterceptor implements HandlerInterceptor {

    private final Authenticator authenticator;
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    public AbstractAuthenticationInterceptor(Authenticator authenticator, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        this.authenticator = authenticator;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            log.debug("인증 절차 요청됨");
            final Authentication authentication = attemptAuthentication(request, response);
            log.debug("AAI#preHandle Authentication: {}", authentication);
            handleSuccess(request, response, authentication);
        } catch (AuthenticationException e) {
            log.error(e.getMessage(), e);
            handleFailure(request, response, e);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            handleFailure(request, response, new AuthenticationException("Fatal error occurred on authentication process", e));
        }
        return false;
    }

    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final SecurityContext ctx = new SecurityContext(authentication);
        SecurityContextHolder.setContext(ctx);
        successHandler.onSuccess(request, response, authentication);
    }

    private void handleFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        SecurityContextHolder.clear();
        failureHandler.onFailure(request, response, e);
    }

    protected Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final AuthenticationToken token = convert(request);
        return authenticator.authenticate(token);
    }

    protected AuthenticationToken convert(HttpServletRequest request) {
        throw new IllegalStateException("Not implemented yet.");
    }
}
