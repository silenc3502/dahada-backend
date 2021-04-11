package com.dahada.backend.application.authentication;

import com.dahada.backend.application.authentication.dto.OAuth2Attributes;

public interface SessionAuthRepository {
    void storeOAuth2Data(OAuth2Attributes attributes);
    OAuth2Attributes restoreOAuth2Data();
}
