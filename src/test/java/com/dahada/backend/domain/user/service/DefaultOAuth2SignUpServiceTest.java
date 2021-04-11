package com.dahada.backend.domain.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OAuth2 사용자 가입 테스트")
class DefaultOAuth2SignUpServiceTest {

    @Mock
    private UserQueryService queryService;

    private DefaultOAuth2SignUpService sut;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("사용자가 이미 존재하는 경우 UserAlreadyExistException 예외 발생")
    @Test
    void testUserAlreadyExist() {
    }
}