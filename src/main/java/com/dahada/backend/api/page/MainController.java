package com.dahada.backend.api.page;

import com.dahada.backend.api.page.dto.ReservationForm;
import com.dahada.backend.application.aspect.RequestTracking;
import com.dahada.backend.application.configuration.resolvers.annotations.Authenticated;
import com.dahada.backend.application.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author nobody
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestTracking
@RequestMapping("/")
public class MainController {

    private final HttpSession session;

    @GetMapping
    public String index(@Authenticated UserInfo info, Model model) {
        model.addAttribute("user", info);
        return "main";
    }

    @GetMapping("/reservation-step1")
    public String reservation1() {
        return "reservation-step1";
    }

    @GetMapping("/reservation-step2")
    public String reservation2(Model model) {
        final ReservationForm form = (ReservationForm) session.getAttribute("reservationForm");
        if (form == null) {
            return "redirect:/reservation-step1";
        }
        model.addAttribute("form", form);
        return "reservation-step2";
    }

    @GetMapping("/reservation-step3")
    public String reservation3() {
        return "reservation-step3";
    }

    @GetMapping("/reservation-step4")
    public String reservation4() {
        return "reservation-step4";
    }
}
