package com.dahada.backend.application.auth.service;

import com.dahada.backend.application.configuration.tyeps.CacheType;
import com.dahada.backend.domain.common.vo.Email;
import com.dahada.backend.domain.user.UserRepository;
import com.dahada.backend.domain.user.enitity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DefaultOAuth2UserDetailsService implements OAuth2UserDetailsService {

    private final UserRepository repository;

    public DefaultOAuth2UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(
            cacheNames = CacheType.USER_DETAILS_CACHE_NAME,
            key = "#email",
            unless = "#result == null"
    )
    public Optional<UserDetails> loadUserByEmail(String email) {
        final Optional<User> maybeUser = repository.findUserByEmail(Email.of(email));
        if (maybeUser.isPresent()) {
            final User user = maybeUser.get();
            return Optional.of(OAuth2UserPrincipal.ofRegisteredUser(user));
        }
        return Optional.empty();
    }
}
