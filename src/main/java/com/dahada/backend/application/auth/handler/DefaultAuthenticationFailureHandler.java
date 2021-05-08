package com.dahada.backend.application.auth.handler;

import com.dahada.backend.application.auth.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.error(exception.getMessage(), exception);
        log.error("error occurred at {}", request.getRequestURI());
        redirect(response);
    }

    private void redirect(HttpServletResponse response) {
        try {
            response.sendRedirect("/error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
