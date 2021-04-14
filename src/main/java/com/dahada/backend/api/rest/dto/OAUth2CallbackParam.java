package com.dahada.backend.api.rest.dto;

import com.dahada.backend.application.auth.interceptor.OAuth2CallbackParamHolder;
import com.dahada.backend.domain.authentication.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OAUth2CallbackParam {
    private Provider provider;
    private String code;
    private String state;

    public OAUth2CallbackParam(HttpServletRequest request) {
        final String uri = request.getRequestURI();
        final int i = uri.lastIndexOf("/");
        final Provider provider = Provider.valueOf(uri.substring(i + 1).toUpperCase());
        final Map<String, String[]> paramMap = request.getParameterMap();
        this.provider = provider;
        this.code = paramMap.get("code")[0];
        this.state = Optional.ofNullable(paramMap.get("state")).map(it -> it[0]).orElse(null);
    }

    public OAuth2CallbackParamHolder toHolder() {
        return new OAuth2CallbackParamHolder(provider, code, state);
    }
}
