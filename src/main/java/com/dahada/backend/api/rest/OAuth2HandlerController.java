package com.dahada.backend.api.rest;

import com.dahada.backend.application.auth.OAuth2ServiceFactory;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequestMapping("/oauth")
@RestController
public class OAuth2HandlerController {

    @GetMapping("/authorization/{provider}")
    public void redirectAuthorization(@PathVariable String provider, HttpServletResponse response) throws IOException {
        final OAuth20Service service = OAuth2ServiceFactory.valueOf(provider.toUpperCase()).getService();
        final String authorizationUrl = service.getAuthorizationUrl();
        log.debug("authorizationUrl: {}", authorizationUrl);
        response.sendRedirect(authorizationUrl);
    }
}
