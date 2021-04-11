package com.dahada.backend.domain.common.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("이메일 값객체 테스트")
class EmailTest {

    @DisplayName("이메일 형식이 잘못된 경우 IllegalArgumentException 예외 발생")
    @Test
    void testWrongEmailPattern() {
        final String wrongFormattedEmail = "abc";
        assertThrows(IllegalArgumentException.class, () -> {
            Email.of(wrongFormattedEmail);
        });
    }
}