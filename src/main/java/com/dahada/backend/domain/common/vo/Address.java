package com.dahada.backend.domain.common.vo;

import com.dahada.backend.domain.common.utils.StringUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 주소 값객체
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

    /**
     * 시/읍/면
     */
    private String city;

    /**
     * 도로
     */
    private String street;

    /**
     * 상세 주소
     */
    private String detail;

    public static Address of(String city, String street, String detail) {
        StringUtils.hasText(city, () -> { throw new IllegalArgumentException("도시 정보는 필수입니다."); });
        StringUtils.hasText(street, () -> { throw new IllegalArgumentException("도로 정보는 필수입니다."); });
        StringUtils.hasText(detail, () -> { throw new IllegalArgumentException("상세 정보는 필수입니다."); });
        return new Address(city, street, detail);
    }
}
