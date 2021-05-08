package com.dahada.backend.application.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public final class AuthenticationToken {
    private final String principal;
    private final String credential;
}
