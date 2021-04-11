package com.dahada.backend.domain.user.enitity;

import com.dahada.backend.domain.common.entity.BaseTimeEntity;
import com.dahada.backend.domain.common.vo.Email;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 일반 사용자 도메인
 *
 * @author hyeyoom
 */
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_user_email", columnNames = {"email"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Embedded
    @Column(nullable = false)
    private Email email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.DAHADA_USER;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    private UserProfile profile;

    public User(String email, String name) {
        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("The length of name should be greater than 1");
        }

        this.email = Email.of(email);
        this.name = name;
    }
}
