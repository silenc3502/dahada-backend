package com.dahada.backend.application.auth.handler;

import com.dahada.backend.application.auth.dto.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hyeyoom
 */
public interface AuthenticationSuccessHandler {
    void onSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
