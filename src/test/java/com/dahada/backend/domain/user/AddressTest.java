package com.dahada.backend.domain.user;

import com.dahada.backend.domain.common.vo.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("주소 값객체(VO) 테스트")
class AddressTest {

    @DisplayName("도시 정보는 필수임")
    @Test
    void testCityIsRequired() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address.of("", "도로랑", "상세주소는 있어");
        });
    }

    @DisplayName("도로명 정보는 필수임")
    @Test
    void testStreetIsRequired() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address.of("주소랑", "", "상세주소는 있어");
        });
    }

    @DisplayName("상세 정보는 필수임")
    @Test
    void testDetailIsRequired() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address.of("주소랑", "도로 정보만 있음", "");
        });
    }
}