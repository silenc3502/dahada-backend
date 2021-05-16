package com.dahada.backend.api.rest;

import com.dahada.backend.application.auth.SecurityContextHolder;
import com.dahada.backend.application.auth.utils.CookieUtil;
import com.dahada.backend.application.configuration.props.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final JwtProperties properties;

    // TODO: 서비스로 잘 빼라
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CookieUtil.invalidate(request, response, properties.getTokenPolicy().getTokenNames());
        SecurityContextHolder.clear();
        response.sendRedirect("/");
    }
}
