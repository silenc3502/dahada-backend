package com.dahada.backend.application.auth;

import com.dahada.backend.application.auth.dto.Authentication;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class SecurityContext {
    private final Authentication authentication;
}
