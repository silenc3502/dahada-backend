package com.dahada.backend.application.configuration;

import com.dahada.backend.application.authentication.CustomOAuth2UserService;
import com.dahada.backend.application.authentication.OAuth2SuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService service;
    private final OAuth2SuccessHandler successHandler;

    public SecurityConfig(CustomOAuth2UserService service, OAuth2SuccessHandler successHandler) {
        this.service = service;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/js/**", "/img/**", "/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout().logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .successHandler(successHandler)
                        .userInfoEndpoint().userService(service);
    }
}
