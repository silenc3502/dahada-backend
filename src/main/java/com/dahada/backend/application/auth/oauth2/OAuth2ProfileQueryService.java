package com.dahada.backend.application.auth.oauth2;

import com.dahada.backend.application.auth.OAuth2ServiceFactory;
import com.dahada.backend.application.auth.interceptor.OAuth2ProfileRequest;
import com.dahada.backend.domain.common.utils.ConvertUtil;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class OAuth2ProfileQueryService {

    public OAuth2UserInfo requestProfile(OAuth2ProfileRequest request) {
        try {
            final OAuth2ServiceFactory serviceHolder = OAuth2ServiceFactory.getServiceHolder(request.getProvider());
            final OAuth20Service service = serviceHolder.getService();
            final OAuth2AccessToken token = service.getAccessToken(request.getCode());
            log.debug("token: {}", token);

            final OAuthRequest oAuthRequest = new OAuthRequest(serviceHolder.getVerb(), serviceHolder.getResourceUrl());
            service.signRequest(token, oAuthRequest);

            final Response execute = service.execute(oAuthRequest);
            log.debug("body: {}", execute.getBody());

            final Map<String, Object> attributes = ConvertUtil.toMap(execute.getBody());
            final OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.get(request.getProvider(), attributes);
            log.debug("oAuth2UserInfo: {}", oAuth2UserInfo);
            return oAuth2UserInfo;
        } catch (IOException | InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
