package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;

/**
 * @author nobody
 */
public interface OAuth2SignUpService {
    String signUp(OAuth2SignUpUserRequest request);
}
