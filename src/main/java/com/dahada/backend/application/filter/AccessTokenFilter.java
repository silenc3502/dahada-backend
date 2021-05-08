package com.dahada.backend.application.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Order(FilterConstant.ACCESS_TOKEN_FILTER_ORDER)
@Component
@RequiredArgsConstructor
public class AccessTokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("AccessTokenFilter has been initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("AccessTokenFilter#doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("AccessTokenFilter has been destroyed.");
    }
}
