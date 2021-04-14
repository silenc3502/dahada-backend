package com.dahada.backend.application.auth.interceptor;

import com.dahada.backend.domain.authentication.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class OAuth2CallbackParamHolder {
    private Provider provider;
    private String code;
    private String state;
}
