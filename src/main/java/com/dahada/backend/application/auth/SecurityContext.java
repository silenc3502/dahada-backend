package com.dahada.backend.application.auth;

import com.dahada.backend.application.auth.dto.Authentication;
import com.dahada.backend.application.auth.service.UserDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class SecurityContext {
    private final Authentication authentication;

    public static SecurityContext of(UserDetails userDetails) {
        return new SecurityContext(new Authentication(userDetails));
    }
}
