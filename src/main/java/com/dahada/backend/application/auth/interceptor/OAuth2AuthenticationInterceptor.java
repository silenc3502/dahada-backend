package com.dahada.backend.application.auth.interceptor;

import com.dahada.backend.application.auth.dto.AuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Service
public class OAuth2AuthenticationInterceptor extends AbstractAuthenticationInterceptor {

    @Override
    AuthenticationToken convert(HttpServletRequest request) {
        log.debug("request uri: {}", request.getRequestURI());
        final Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()) {
            log.debug("abc: {}", parameterMap.get(key)[0]);
        }
        log.debug("request uri: {}", request.getParameterMap());
        return null;
    }
}
