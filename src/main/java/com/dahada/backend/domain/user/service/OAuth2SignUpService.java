package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;

/**
 * @author hyeyoom
 */
public interface OAuth2SignUpService {
    void signUp(OAuth2SignUpUserRequest request);
}
