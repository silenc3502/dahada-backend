package com.dahada.backend.domain.user.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CheckUserExistenceRequest {
    private final String email;
}
