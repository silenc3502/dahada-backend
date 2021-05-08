package com.dahada.backend.application.auth.handler;

import com.dahada.backend.application.auth.exception.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nobody
 */
public interface AuthenticationFailureHandler {
    void onFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception);
}
