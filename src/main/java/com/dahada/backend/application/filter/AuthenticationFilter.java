package com.dahada.backend.application.filter;

import com.dahada.backend.application.auth.provider.TokenProvider;
import com.dahada.backend.application.auth.utils.CookieUtil;
import com.dahada.backend.application.configuration.props.JwtProperties;
import com.dahada.backend.lang.Triple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * access token 발급 자동화를 위한 필터.
 * NOTE: 보안 상 refresh_token을 이용해 access_token을 발급하는 절차가 필요함.
 */
@Slf4j
@Order(FilterConstant.AUTHENTICATION_FILTER_ORDER)
@Component
public class AuthenticationFilter implements Filter {

    private final JwtProperties jwtProperties;
    private final TokenProvider tokenProvider;
    private final String accessTokenName;
    private final String refreshTokenName;

    public AuthenticationFilter(JwtProperties jwtProperties, TokenProvider tokenProvider) {
        this.jwtProperties = jwtProperties;
        this.tokenProvider = tokenProvider;
        accessTokenName = jwtProperties.getTokenPolicy().getAccessTokenName();
        refreshTokenName = jwtProperties.getTokenPolicy().getRefreshTokenName();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("AuthenticationFilter has been initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (CookieUtil.has(httpRequest, refreshTokenName) && CookieUtil.hasNot(httpRequest, accessTokenName)) {
            process(httpRequest, httpResponse);
        }
        chain.doFilter(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        final Cookie refreshTokenCookie = CookieUtil.getCookie(request, refreshTokenName);
        if (refreshTokenCookie == null) {
            return;
        }
        final String refreshToken = refreshTokenCookie.getValue();
        if (!tokenProvider.checkTokenValidation(refreshToken)) {
            log.warn("Invalid token found.");
            CookieUtil.invalidate(request, response, jwtProperties.getTokenPolicy().getTokenNames());
            return;
        }

        log.debug("Found refreshToken: {}", refreshToken);
        issueAccessToken(refreshToken, response);
    }

    private void issueAccessToken(String refreshToken, HttpServletResponse response) {
        final Map<String, Object> payload = tokenProvider.extractPayloadFromToken(refreshToken);
        log.debug("payload from refresh token: {}", payload);
        final Triple<String, String, Long> results = tokenProvider.issueAccessToken((Map<String, Object>) payload.get("info"));
        setCookieForResponse(results, response);
    }

    private void setCookieForResponse(Triple<String, String, Long> issuedTokenInfo, HttpServletResponse response) {
        final Cookie cookie = new Cookie(issuedTokenInfo.getFirst(), issuedTokenInfo.getSecond());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Math.toIntExact(issuedTokenInfo.getThird()));
        response.addCookie(cookie);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("AuthenticationFilter has been destroyed.");
    }
}
