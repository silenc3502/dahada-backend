package com.dahada.backend.application.auth.interceptor;

import com.dahada.backend.application.auth.dto.Authentication;
import com.dahada.backend.application.auth.oauth2.OAuth2ProfileQueryService;
import com.dahada.backend.application.auth.oauth2.OAuth2UserInfo;
import com.dahada.backend.application.auth.service.OAuth2UserDetailsService;
import com.dahada.backend.application.auth.service.OAuth2UserPrincipal;
import com.dahada.backend.application.auth.service.UserDetails;
import com.dahada.backend.domain.authentication.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2AuthenticationInterceptor extends AbstractAuthenticationInterceptor {

    private final OAuth2ProfileQueryService oAuth2ProfileQueryService;
    private final OAuth2UserDetailsService detailsService;


    /**
     * OAuth2는 이미 인증이 완료된 후에 오기 때문에 UserPrincipal만 넘겨줌.
     *
     * @param request  http request
     * @param response http response
     * @return 인증 객체
     */
    @Override

    protected Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final OAuth2ProfileRequest profileRequest = parseParameter(request);
        final OAuth2UserInfo oauth2UserInfo = oAuth2ProfileQueryService.requestProfile(profileRequest);
        log.debug("oauth2UserInfo: {}", oauth2UserInfo);
        final UserDetails userDetails = detailsService
                .loadUserByEmail(oauth2UserInfo.getEmail())
                .orElseGet(() -> OAuth2UserPrincipal.ofUnregisteredUser(oauth2UserInfo));
        log.debug("userDetails: {}", userDetails);
        return new Authentication(userDetails);
    }

    private OAuth2ProfileRequest parseParameter(HttpServletRequest request) {
        final String uri = request.getRequestURI();
        final int i = uri.lastIndexOf("/");
        final Provider provider = Provider.valueOf(uri.substring(i + 1).toUpperCase());
        final Map<String, String[]> paramMap = request.getParameterMap();
        final String code = paramMap.get("code")[0];
        final String state = Optional.ofNullable(paramMap.get("state")).map(it -> it[0]).orElse(null);
        return new OAuth2ProfileRequest(provider, code, state);
    }
}
