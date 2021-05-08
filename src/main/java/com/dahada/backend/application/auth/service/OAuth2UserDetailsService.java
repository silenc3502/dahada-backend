package com.dahada.backend.application.auth.service;

import java.util.Optional;

public interface OAuth2UserDetailsService {
    Optional<UserDetails> loadUserByEmail(String email);
}
