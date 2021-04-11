package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.user.UserRepository;
import com.dahada.backend.domain.user.enitity.User;
import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DefaultUserQueryService implements UserQueryService {

    private final UserRepository repository;

    public DefaultUserQueryService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean exist(CheckUserExistenceRequest request) {
        final Optional<User> maybeUser = repository.findUserByEmail(request.getEmail());
        return maybeUser.isPresent();
    }
}
