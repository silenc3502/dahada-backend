package com.dahada.backend.infra.session;

import com.dahada.backend.application.authentication.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class DefaultSessionRepository implements SessionRepository {

    private final HttpSession session;

    public DefaultSessionRepository(HttpSession session) {
        this.session = session;
    }

}
