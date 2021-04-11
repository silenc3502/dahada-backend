package com.dahada.backend.domain.user;

import com.dahada.backend.domain.user.enitity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 도메인 테스트")
class UserTest {

    @DisplayName("사용자 이름이 두 글자 미만이면 IllegalArgumentException 예외 발생")
    @Test
    void testLengthOfUserName() {
        // arrange
        final String mail = "abc@mail.com";
        final String name = "한";

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            final User user = new User(mail, name);
        });
    }

    @DisplayName("사용자 이메일 형식이 잘못된 경우 IllegalArgumentException 예외 발생")
    @Test
    void testWrongEmailPattern() {
        // arrange
        final String mail = "abc";
        final String name = "하나";

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            final User user = new User(mail, name);
        });
    }
}