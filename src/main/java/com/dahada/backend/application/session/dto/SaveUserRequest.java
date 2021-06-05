package com.dahada.backend.application.session.dto;

import lombok.Getter;

@Getter
public class SaveUserRequest {

    private final String email;

    public SaveUserRequest(String email) {
        this.email = email;
    }

    public SessionUser toSessionUser() {
        return new SessionUser(email);
    }
}
