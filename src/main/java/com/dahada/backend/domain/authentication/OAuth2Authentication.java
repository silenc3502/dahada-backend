package com.dahada.backend.domain.authentication;

import com.dahada.backend.domain.common.converter.MapAttributeConverter;
import com.dahada.backend.domain.common.entity.BaseTimeEntity;
import com.dahada.backend.domain.common.vo.Email;
import com.dahada.backend.domain.user.enitity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

/**
 * @author nobody
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "oauth2_authentication"
)
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
     * 제공자 타입
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    /**
     * 인증 결과로 받은 json 값
     */
    @Lob
    @Convert(converter = MapAttributeConverter.class)
    @Column(nullable = false)
    private Map<String, Object> attributes;

    public OAuth2Authentication(User user, Email email, Provider provider, Map<String, Object> attributes) {
        this.user = user;
        this.email = email;
        this.provider = provider;
        this.attributes = attributes;
    }
}
