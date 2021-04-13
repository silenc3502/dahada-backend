package com.dahada.backend.application.auth.provider;

import com.dahada.backend.application.auth.dto.Authentication;
import com.dahada.backend.application.auth.dto.AuthenticationToken;
import org.springframework.lang.Nullable;

/**
 * 인증 확인 모듈
 *
 * @author hyeyoom
 */
public interface Authenticator {
    @Nullable
    Authentication authenticate(AuthenticationToken token);
}
