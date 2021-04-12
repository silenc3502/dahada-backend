package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.common.vo.Email;
import com.dahada.backend.domain.user.UserRepository;
import com.dahada.backend.domain.user.enitity.User;
import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("사용자 조회 서비스 테스트")
class DefaultUserQueryServiceTest {

    @Mock
    private UserRepository repository;

    private DefaultUserQueryService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new DefaultUserQueryService(repository);
    }

    @DisplayName("사용자가 레포지토리에 없는 경우 false 반환")
    @Test
    void testNonExistenceUser() {
        // given
        final CheckUserExistenceRequest request = new CheckUserExistenceRequest("abc@adfadsf.com");

        // stub
        when(repository.findUserByEmail(any(Email.class))).thenReturn(Optional.empty());

        // when
        final boolean actual = sut.exist(request);

        // then
        assertFalse(actual);
    }

    @DisplayName("사용자가 레포지토리에 존재하는 경우 true 반환")
    @Test
    void testExistenceUser() {
        // given
        final CheckUserExistenceRequest request = new CheckUserExistenceRequest("abc@adfadsf.com");

        // stub
        final User mockUser = mock(User.class);
        when(repository.findUserByEmail(any(Email.class))).thenReturn(Optional.of(mockUser));

        // when
        final boolean actual = sut.exist(request);

        // then
        assertTrue(actual);
    }
}