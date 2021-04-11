package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.common.exception.UserAlreadyExistException;
import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;
import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultOAuth2SignUpService implements OAuth2SignUpService {

    private UserQueryService queryService;

    @Override
    public void signUp(OAuth2SignUpUserRequest request) {
        final CheckUserExistenceRequest checkExistence = new CheckUserExistenceRequest(request.getEmail());
        if (queryService.exist(checkExistence)) {
            throw new UserAlreadyExistException("사용자가 이미 존재합니다.");
        }
    }
}
