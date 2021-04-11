package com.dahada.backend.application.authentication.dto;

import com.dahada.backend.domain.authentication.Provider;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public final class OAuth2Attributes {
    private final Map<String, Object> attributes;
    private final Provider provider;
    private final String uniqueId;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, Provider provider, String uniqueId, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.provider = provider;
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
                    return ofGoogle(provider, userNameAttributeKey, attributes);
                case NAVER:
                    return ofNaver(provider, "id", attributes);
                case KAKAO:
                    return ofKakao(provider, "id", attributes);
                default:
                    return null;
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("OAuth2 arguments are wrong.");
        }
    }

    private static OAuth2Attributes ofGoogle(Provider provider, String userNameAttributeKey, Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .attributes(attributes)
                .provider(provider)
                .uniqueId((String) attributes.get(userNameAttributeKey))
                .nameAttributeKey(userNameAttributeKey)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    @SuppressWarnings("unchecked")
    private static OAuth2Attributes ofNaver(Provider provider, String userNameAttributeKey, Map<String, Object> attributes) {
        final Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuth2Attributes.builder()
                .attributes(response)
                .provider(provider)
                .uniqueId((String) response.get(userNameAttributeKey))
                .nameAttributeKey(userNameAttributeKey)
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .build();
    }

    @SuppressWarnings("unchecked")
    private static OAuth2Attributes ofKakao(Provider provider, String userNameAttributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) response.get("profile");
        return OAuth2Attributes.builder()
                .attributes(attributes)
                .provider(provider)
                .uniqueId(String.valueOf(attributes.get(userNameAttributeKey)))
                .nameAttributeKey(userNameAttributeKey)
                .name((String) profile.get("nickname"))
                .email((String) response.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .build();
    }
}
