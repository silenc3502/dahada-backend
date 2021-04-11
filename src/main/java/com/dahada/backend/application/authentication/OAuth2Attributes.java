package com.dahada.backend.application.authentication;

import com.dahada.backend.domain.authentication.Provider;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public final class OAuth2Attributes {
    private final Map<String, Object> attributes;
    private final String uniqueId;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;

    @Builder
    private OAuth2Attributes(Map<String, Object> attributes, String uniqueId, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.uniqueId = uniqueId;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeKey, Map<String, Object> attributes) {
        try {
            final Provider provider = Provider.valueOf(registrationId.toUpperCase());
            switch (provider) {
                case GOOGLE:
                    return ofGoogle(userNameAttributeKey, attributes);
                case NAVER:
                    return ofNaver("id", attributes);
                case KAKAO:
                    return ofKakao("id", attributes);
                default:
                    return null;
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("OAtuh2 arguments are wrong.");
        }
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeKey, Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .attributes(attributes)
                .uniqueId((String) attributes.get(userNameAttributeKey))
                .nameAttributeKey(userNameAttributeKey)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    @SuppressWarnings("unchecked")
    private static OAuth2Attributes ofNaver(String userNameAttributeKey, Map<String, Object> attributes) {
        final Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuth2Attributes.builder()
                .attributes(response)
                .uniqueId((String) response.get(userNameAttributeKey))
                .nameAttributeKey(userNameAttributeKey)
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .build();
    }

    @SuppressWarnings("unchecked")
    private static OAuth2Attributes ofKakao(String userNameAttributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) response.get("profile");
        return OAuth2Attributes.builder()
                .attributes(attributes)
                .uniqueId(String.valueOf(attributes.get(userNameAttributeKey)))
                .nameAttributeKey(userNameAttributeKey)
                .name((String) profile.get("nickname"))
                .email((String) response.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .build();
    }
}
