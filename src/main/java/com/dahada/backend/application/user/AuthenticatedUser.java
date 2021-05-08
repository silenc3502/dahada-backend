package com.dahada.backend.application.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class AuthenticatedUser {
    private final String email;
    private final String name;
    private final String signature;
}
