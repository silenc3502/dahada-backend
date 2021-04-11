package com.dahada.backend.infra.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class SessionService {

    private final HttpSession session;

    public SessionService(HttpSession session) {
        this.session = session;
    }

}
