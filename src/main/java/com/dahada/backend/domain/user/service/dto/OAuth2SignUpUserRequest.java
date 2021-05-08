package com.dahada.backend.domain.user.service.dto;

import com.dahada.backend.domain.authentication.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class OAuth2SignUpUserRequest {
    private final String name;
    private final String email;

    private final Provider provider;
    private final Map<String, Object> attributes;
}
