package com.dahada.backend.domain.user;

import com.dahada.backend.domain.common.vo.Address;
import com.dahada.backend.domain.user.enitity.User;
import com.dahada.backend.domain.user.enitity.UserProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 프로파일 테스트")
class UserProfileTest {

    private User dummyUser = new User("abc@def.com", "다하다");
    private Address dummyAddress = Address.of("서울시", "테헤란로", "역삼역에살고있어요");

    @DisplayName("사용자 정보가 누락 시 IllegalArgumentException 발생")
    @Test
    void testUserEntityWasOmitted() {
        assertThrows(IllegalArgumentException.class, () -> {
            final UserProfile profile = new UserProfile(null, dummyAddress, "01012345678");
        });
    }

    @DisplayName("핸드폰 정보 누락 시 IllegalArgumentException 발생")
    @Test
    void test() {
        assertThrows(IllegalArgumentException.class, () -> {
            final UserProfile profile = new UserProfile(dummyUser, dummyAddress, "");
        });
    }
}