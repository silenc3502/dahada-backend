package com.dahada.backend.domain.user.service;

import com.dahada.backend.application.auth.service.OAuth2UserDetailsService;
import com.dahada.backend.application.auth.service.UserDetails;
import com.dahada.backend.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultOAuth2UserDetailsService implements OAuth2UserDetailsService {

    private final UserRepository repository;

    public DefaultOAuth2UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        return null;
    }
}
