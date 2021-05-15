package com.dahada.backend.application.auth.service;

public interface UserDetails {
    String getSignature();
    String getEmail();
    String getName();
}
