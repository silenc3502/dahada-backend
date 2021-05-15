package com.dahada.backend.application.filter;

import com.dahada.backend.application.auth.SecurityContext;
import com.dahada.backend.application.auth.SecurityContextHolder;
import com.dahada.backend.application.auth.provider.TokenProvider;
import com.dahada.backend.application.configuration.props.JwtProperties;
import com.dahada.backend.application.user.UserInfo;
import com.dahada.backend.domain.common.utils.ConvertUtil;
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

@Slf4j
@Order(FilterConstant.ACCESS_TOKEN_FILTER_ORDER)
@Component
@RequiredArgsConstructor
public class AccessTokenFilter implements Filter {

    private final JwtProperties jwtProperties;
    private final TokenProvider tokenProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("AccessTokenFilter has been initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (hasAccessToken((HttpServletRequest) request)) {
            log.debug("AccessTokenFilter#doFilter");
            final Optional<Cookie> maybeCookie = extractTokenCookie((HttpServletRequest) request, jwtProperties.getTokenPolicy().getAccessTokenName());
            if (maybeCookie.isPresent()) {
                final String accessToken = maybeCookie.get().getValue();
                if (!tokenProvider.checkTokenValidation(accessToken)) {
                    log.warn("잘못된 토큰으로 요청하였습니다.");
                    // TODO: 특정 토큰만 무효화 하도록 변경
                    invalidateTokens((HttpServletRequest) request, (HttpServletResponse) response);
                    SecurityContextHolder.clear();
                    chain.doFilter(request, response);
                    return;
                }
                final UserInfo userInfo = getUserInfo(accessToken);
                setSecurityContext(userInfo);
                log.debug("AuthenticatedUser: {}", userInfo);
            } else {
                log.warn("컨텍스트 무효화.");
                SecurityContextHolder.clear();
            }
        }
        chain.doFilter(request, response);
    }

    private void setSecurityContext(UserInfo userInfo) {
        final SecurityContext ctx = SecurityContext.of(userInfo);
        SecurityContextHolder.setContext(ctx);
    }

    // TODO: 중복 - invalidateTokens
    private void invalidateTokens(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Refresh token is invalid. Cookies are going to be deleted.");
        Arrays.stream(request.getCookies())
                .filter(cookie -> jwtProperties.getTokenPolicy().getTokenNames().contains(cookie.getName()))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });
    }

    private UserInfo getUserInfo(String accessToken) {
        final Map<String, Object> payloadMap = tokenProvider.extractPayloadFromToken(accessToken);
        return ConvertUtil.toObject((Map<String, Object>) payloadMap.get("info"), UserInfo.class);
    }

    private boolean hasAccessToken(HttpServletRequest request) {
        return extractTokenCookie(request, jwtProperties.getTokenPolicy().getAccessTokenName()).isPresent();
    }

    // TODO: 중복
    private Optional<Cookie> extractTokenCookie(HttpServletRequest request, String tokenName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(tokenName))
                .findFirst();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("AccessTokenFilter has been destroyed.");
    }

}
