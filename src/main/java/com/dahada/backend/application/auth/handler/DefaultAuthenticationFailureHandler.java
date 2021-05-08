package com.dahada.backend.application.auth.handler;

import com.dahada.backend.application.auth.exception.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {

    }
}
