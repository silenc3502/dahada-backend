package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.authentication.OAuth2Authentication;
import com.dahada.backend.domain.common.exception.UserAlreadyExistException;
import com.dahada.backend.domain.common.vo.Email;
import com.dahada.backend.domain.user.UserRepository;
import com.dahada.backend.domain.user.enitity.User;
import com.dahada.backend.domain.user.enitity.UserProfile;
import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;
import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class DefaultOAuth2SignUpService implements OAuth2SignUpService {

    private final UserQueryService queryService;
    private final UserRepository repository;

    @Override
    public String signUp(OAuth2SignUpUserRequest request) {
        final CheckUserExistenceRequest checkExistence = new CheckUserExistenceRequest(request.getEmail());
        if (queryService.exist(checkExistence)) {
            throw new UserAlreadyExistException("사용자가 이미 존재합니다.");
        }

        final User newUser = new User(request.getEmail(), request.getName());
        final UserProfile profile = new UserProfile(newUser, null, null);
        final OAuth2Authentication oauth2 = new OAuth2Authentication(
                newUser, Email.of(request.getEmail()), request.getProvider(), request.getAttributes()
        );
        newUser.addProfile(profile);
        newUser.addOAuth2Info(oauth2);
        repository.save(newUser);
        return newUser.getSignature();
    }
}
