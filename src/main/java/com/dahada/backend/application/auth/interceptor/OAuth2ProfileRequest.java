package com.dahada.backend.application.auth.interceptor;

import com.dahada.backend.domain.authentication.Provider;
import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public final class OAuth2ProfileRequest {
    private final Provider provider;
    private final String code;
    private final String state;
}
