package com.dahada.backend.domain.user.enitity;

import com.dahada.backend.domain.authentication.OAuth2Authentication;
import com.dahada.backend.domain.common.entity.BaseTimeEntity;
import com.dahada.backend.domain.common.vo.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 일반 사용자 도메인
 *
 * @author nobody
 */
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_user_email", columnNames = {"email"}),
                @UniqueConstraint(name = "uq_user_signature", columnNames = {"signature"})
        }
)
@Getter
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
    private String signature = generateUserSignature();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.DAHADA_USER;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private UserProfile profile;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private OAuth2Authentication oauth2Authentication;

    public User(String email, String name) {
        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("The length of name should be greater than 1");
        }

        this.email = Email.of(email);
        this.name = name;
    }

    public void addProfile(UserProfile profile) {
        this.profile = profile;
    }

    private String generateUserSignature() {
        final UUID uuid = UUID.randomUUID();
        final long l = ByteBuffer.wrap(uuid.toString().getBytes(StandardCharsets.UTF_8)).getLong();
        return Long.toString(l, 36);
    }
}
