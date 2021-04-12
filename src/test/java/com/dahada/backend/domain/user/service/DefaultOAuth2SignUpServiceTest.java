package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.common.exception.UserAlreadyExistException;
import com.dahada.backend.domain.user.UserRepository;
import com.dahada.backend.domain.user.enitity.User;
import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("OAuth2 사용자 가입 테스트")
class DefaultOAuth2SignUpServiceTest {

    @Mock
    private UserQueryService queryService;

    @Mock
    private UserRepository repository;

    private DefaultOAuth2SignUpService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new DefaultOAuth2SignUpService(queryService, repository);
    }

    @DisplayName("사용자가 이미 존재하는 경우 UserAlreadyExistException 예외 발생")
    @Test
    void testUserAlreadyExist() {
        when(queryService.exist(any())).thenReturn(true);

        final OAuth2SignUpUserRequest request = mock(OAuth2SignUpUserRequest.class);

        assertThrows(UserAlreadyExistException.class, () -> {
            sut.signUp(request);
        });
    }

    @DisplayName("새 사용자 가입을 테스트한다.")
    @Test
    void testSignUpOAuth2() {
        final OAuth2SignUpUserRequest request = mock(OAuth2SignUpUserRequest.class);
        when(queryService.exist(any())).thenReturn(false);
        when(request.getEmail()).thenReturn("aabcd@asdf.com");
        when(request.getName()).thenReturn("aabcd");

        sut.signUp(request);
        verify(repository).save(any(User.class));
    }
}