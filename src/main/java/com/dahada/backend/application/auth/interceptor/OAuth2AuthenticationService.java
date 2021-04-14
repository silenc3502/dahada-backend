package com.dahada.backend.application.auth.interceptor;

import com.dahada.backend.application.auth.OAuth2ServiceFactory;
import com.dahada.backend.application.utils.ConvertUtil;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class OAuth2AuthenticationService {

    public void test(OAuth2CallbackParamHolder holder) {
        try {
            final OAuth2ServiceFactory factory = OAuth2ServiceFactory.valueOf(holder.getProvider().name());
            final OAuth20Service service = factory.getService();
            final OAuth2AccessToken token = service.getAccessToken(holder.getCode());
            log.debug("token: {}", token);
            final OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, factory.getResourceUrl());
            service.signRequest(token, oAuthRequest);
            final Response execute = service.execute(oAuthRequest);
            log.debug("code: {}", execute.getCode());
            log.debug("headers: {}", execute.getHeaders());
            log.debug("body: {}", execute.getBody());
            final Map<String, Object> map = ConvertUtil.toMap(execute.getBody());
            log.debug("map: {}", map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
