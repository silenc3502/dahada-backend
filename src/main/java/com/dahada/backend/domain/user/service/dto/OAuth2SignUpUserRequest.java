package com.dahada.backend.domain.user.service.dto;

import com.dahada.backend.domain.authentication.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class OAuth2SignUpUserRequest {
    private final String email;
    private final Provider provider;
    private final String oauth2Key;


}
