package com.dahada.backend.application.session;

import com.dahada.backend.application.session.dto.SaveUserRequest;

public interface SessionService {
    void save(SaveUserRequest request);
    void logout();
}
