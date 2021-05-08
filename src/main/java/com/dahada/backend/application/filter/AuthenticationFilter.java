package com.dahada.backend.application.filter;

import com.dahada.backend.application.auth.provider.TokenProvider;
import com.dahada.backend.application.configuration.props.JwtProperties;
import com.dahada.backend.lang.Triple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * access token 발급 자동화를 위한 필터.
 * fixme: 보안 상 refresh_token을 이용해 access_token을 발급하는 절차가 필요함.
 */
@Slf4j
@Order(FilterConstant.AUTHENTICATION_FILTER_ORDER)
@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private final JwtProperties jwtProperties;
    private final TokenProvider tokenProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("AuthenticationFilter has been initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (hasNotAccessToken((HttpServletRequest) request)) {
            process((HttpServletRequest) request, (HttpServletResponse) response);
            log.debug("AuthenticationFilter#doFilter#process");
        }
        chain.doFilter(request, response);
    }

    private boolean hasNotAccessToken(HttpServletRequest request) {
        return extractTokenCookie(request, jwtProperties.getTokenPolicy().getAccessTokenName()).isEmpty();
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        final Optional<Cookie> maybeRefreshTokenCookie
                = extractTokenCookie(request, jwtProperties.getTokenPolicy().getRefreshTokenName());
        if (maybeRefreshTokenCookie.isEmpty()) {
            log.debug("refreshToken not found.");
            return;
        }

        final String refreshToken = processRefreshToken(request, response, maybeRefreshTokenCookie);
        log.debug("Found refreshToken: {}", refreshToken);

        if (refreshToken != null) {
            issueAccessToken(refreshToken, response);
        }
    }

    private void issueAccessToken(String refreshToken, HttpServletResponse response) {
        final Map<String, Object> payload = tokenProvider.extractPayloadFromToken(refreshToken);
        log.debug("payload from refresh token: {}", payload);
        final Triple<String, String, Long> results = tokenProvider.issueAccessToken((Map<String, Object>) payload.get("info"));
        setCookieForResponse(results, response);
    }

    private String processRefreshToken(HttpServletRequest request, HttpServletResponse response, Optional<Cookie> maybeRefreshTokenCookie) {
        final String token = extractTokenFromCookie(maybeRefreshTokenCookie);
        if (!tokenProvider.checkTokenValidation(token)) {
            invalidateTokens(request, response);
        }
        return token;
    }

    private void invalidateTokens(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Refresh token is invalid. Cookies are going to be deleted.");
        Arrays.stream(request.getCookies())
                .filter(cookie -> jwtProperties.getTokenPolicy().getTokenNames().contains(cookie.getName()))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });
    }

    private Optional<Cookie> extractTokenCookie(HttpServletRequest request, String tokenName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(tokenName))
                .findFirst();
    }

    private String extractTokenFromCookie(Optional<Cookie> refreshTokenCookie) {
        if (refreshTokenCookie.isEmpty()) {
            return null;
        }
        return refreshTokenCookie.get().getValue();
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
