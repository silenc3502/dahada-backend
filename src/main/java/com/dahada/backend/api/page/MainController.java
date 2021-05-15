package com.dahada.backend.api.page;

import com.dahada.backend.application.aspect.RequestTracking;
import com.dahada.backend.application.configuration.resolvers.annotations.Authenticated;
import com.dahada.backend.application.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nobody
 */
@Slf4j
@Controller
@RequestTracking
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(
            @Authenticated UserInfo info,
            Model model
    ) {
        log.debug("info: {}", info);
        model.addAttribute("user", info);
        return "main";
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "reservation";
    }
}
