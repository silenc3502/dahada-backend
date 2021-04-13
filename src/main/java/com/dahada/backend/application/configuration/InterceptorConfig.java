package com.dahada.backend.application.configuration;

import com.dahada.backend.application.auth.interceptor.OAuth2AuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final OAuth2AuthenticationInterceptor oauth2Interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(oauth2Interceptor).addPathPatterns("/oauth/callback/**");
    }
}
