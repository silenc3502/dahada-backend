package com.dahada.backend.domain.user.enitity;

import com.dahada.backend.domain.common.entity.BaseTimeEntity;
import com.dahada.backend.domain.common.utils.StringUtils;
import com.dahada.backend.domain.common.vo.Address;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * 일반 사용자 개인 정보
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private Address address = null;

    private String phone = null;

    public UserProfile(User user, Address address, String phone) {
        this.user = user;
        this.address = address;
        this.phone = phone;
    }
}
