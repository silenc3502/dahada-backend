package com.dahada.backend.domain.authentication;

import com.dahada.backend.domain.common.converter.MapAttributeConverter;
import com.dahada.backend.domain.common.entity.BaseTimeEntity;
import com.dahada.backend.domain.common.vo.Email;
import com.dahada.backend.domain.user.enitity.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.Map;

/**
 * @author nobody
 */
@Entity
@Getter
public class OAuth2Authentication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private Email email;

    /**
     * OAuth2.0 제공자들이 부여하는 key
     */
    @Column(nullable = false)
    private String oauth2Key;

    /**
     * 제공자 타입
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    /**
     * 인증 결과로 받은 json 값
     */
    @Convert(converter = MapAttributeConverter.class)
    @Column(nullable = false)
    private Map<String, Object> attributes;
}
