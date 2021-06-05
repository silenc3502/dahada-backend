package com.dahada.backend.application.partners.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PartnerSignInRequest {

    private final String email;
    private final String password;
}
