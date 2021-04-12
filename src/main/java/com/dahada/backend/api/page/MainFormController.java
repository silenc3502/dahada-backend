package com.dahada.backend.api.page;

import com.dahada.backend.api.page.form.OAuth2SignUpForm;
import com.dahada.backend.application.aspect.RequestTracking;
import com.dahada.backend.application.authentication.SessionAuthRepository;
import com.dahada.backend.application.authentication.dto.OAuth2Attributes;
import com.dahada.backend.domain.user.service.OAuth2SignUpService;
import com.dahada.backend.domain.user.service.dto.OAuth2SignUpUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestTracking
@RequestMapping("/")
@RequiredArgsConstructor
public class MainFormController {

    private final SessionAuthRepository authRepository;
    private final OAuth2SignUpService oAuth2SignUpService;

    @PostMapping("/signup/oauth2")
    public String oauth2SignUpForm(OAuth2SignUpForm form) {
        log.debug("oauth2SignUpForm-form: {}", form);
        final OAuth2Attributes attributes = authRepository.restoreOAuth2Data();
        final OAuth2SignUpUserRequest request = new OAuth2SignUpUserRequest(
                form.getName(),
                form.getEmail(),
                attributes.getProvider(),
                attributes.getUniqueId()
        );
        oAuth2SignUpService.signUp(request);
        return "redirect:/";
    }
}
