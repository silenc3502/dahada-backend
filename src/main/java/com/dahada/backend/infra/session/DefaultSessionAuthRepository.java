package com.dahada.backend.infra.session;

import com.dahada.backend.application.authentication.SessionAuthRepository;
import com.dahada.backend.application.authentication.dto.OAuth2Attributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class DefaultSessionAuthRepository implements SessionAuthRepository {

    private static final String OAUTH2_TEMP_DATA = "OAUTH2_TEMP_DATA";

    private final HttpSession session;

    public DefaultSessionAuthRepository(HttpSession session) {
        this.session = session;
    }

    @Override
    public void storeOAuth2Data(OAuth2Attributes attributes) {
        session.setAttribute(OAUTH2_TEMP_DATA, attributes);
    }

    @Override
    public OAuth2Attributes restoreOAuth2Data() {
        final Object obj = session.getAttribute(OAUTH2_TEMP_DATA);
        return (OAuth2Attributes) obj;
    }
}
