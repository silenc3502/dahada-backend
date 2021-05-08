package com.dahada.backend.application.auth.service;

public interface OAuth2UserDetailsService {
    UserDetails loadUserByEmail(String email);
}
