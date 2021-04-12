package com.dahada.backend.api.page;

import com.dahada.backend.api.page.form.OAuth2SignUpForm;
import com.dahada.backend.application.aspect.RequestTracking;
import com.dahada.backend.application.authentication.SessionAuthRepository;
import com.dahada.backend.application.authentication.dto.OAuth2Attributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hyeyoom
 */
@Slf4j
@Controller
@RequestTracking
@RequestMapping("/")
public class MainController {

    private final SessionAuthRepository authRepository;

    public MainController(SessionAuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @GetMapping
    public String index() {
        return "main";
    }

    @GetMapping("/signup/oauth2")
    public String singUpOAuth2(Model model) {
        final OAuth2Attributes oauth2 = authRepository.restoreOAuth2Data();
        log.debug("oauth2: {}", oauth2);
        final OAuth2SignUpForm form = OAuth2SignUpForm
                .builder()
                .name(oauth2.getName())
                .email(oauth2.getEmail())
                .picture(oauth2.getPicture())
                .build();
        model.addAttribute("signUpForm", form);
        return "oauth2signup";
    }
}
