package com.dahada.backend.domain.authentication;

import com.dahada.backend.domain.common.entity.BaseTimeEntity;
import com.dahada.backend.domain.common.vo.Email;
import com.dahada.backend.domain.user.enitity.User;

import javax.persistence.*;

/**
 * @author nobody
 */
@Entity
public class OAuth2Authentication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
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
    @Column(nullable = false)
    private String payload;
}
