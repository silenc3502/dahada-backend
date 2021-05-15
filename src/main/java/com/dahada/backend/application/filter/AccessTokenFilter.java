package com.dahada.backend.application.filter;

import com.dahada.backend.application.auth.SecurityContext;
import com.dahada.backend.application.auth.SecurityContextHolder;
import com.dahada.backend.application.auth.provider.TokenProvider;
import com.dahada.backend.application.auth.utils.CookieUtil;
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
import java.util.Map;

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
        SecurityContextHolder.clear();
        final String accessTokenName = jwtProperties.getTokenPolicy().getAccessTokenName();
        if (CookieUtil.has((HttpServletRequest) request, accessTokenName)) {
            final Cookie cookie = CookieUtil.getCookie((HttpServletRequest) request, accessTokenName);
            if (checkValidation(cookie)) {
                final UserInfo userInfo = getUserInfo(cookie);
                setSecurityContext(userInfo);
            } else {
                CookieUtil.invalidate((HttpServletResponse) response, cookie);
            }
        }
        chain.doFilter(request, response);
    }

    @SuppressWarnings("UNCHECKED_CAST")
    private UserInfo getUserInfo(Cookie cookie) {
        final String accessToken = cookie.getValue();
        final Map<String, Object> payloadMap = tokenProvider.extractPayloadFromToken(accessToken);
        return ConvertUtil.toObject((Map<String, Object>) payloadMap.get("info"), UserInfo.class);
    }

    private boolean checkValidation(Cookie cookie) {
        return cookie != null && tokenProvider.checkTokenValidation(cookie.getValue());
    }

    private void setSecurityContext(UserInfo userInfo) {
        final SecurityContext ctx = SecurityContext.of(userInfo);
        SecurityContextHolder.setContext(ctx);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("AccessTokenFilter has been destroyed.");
    }

}
