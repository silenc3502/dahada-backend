package com.dahada.backend.application.auth.dto;

import com.dahada.backend.application.auth.service.UserDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public final class Authentication {
    private final UserDetails userDetails;
}
