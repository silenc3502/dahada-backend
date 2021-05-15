package com.dahada.backend.api.page;

import com.dahada.backend.api.page.dto.ReservationForm;
import com.dahada.backend.application.aspect.RequestTracking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestTracking
@RequestMapping("/")
public class MainFormController {

    private final HttpSession session;

    @PostMapping("/reservation-step1")
    public String reservation1(@RequestBody ReservationForm form) {
        log.debug("step1 - {}", form);
        session.setAttribute("reservationForm", form);
        return "redirect:/";
    }

    @PostMapping("/reservation-step2")
    public String reservation2(@RequestBody ReservationForm form) {
        log.debug("step2 - {}", form);
        session.setAttribute("reservationForm", form);
        return "redirect:/";
    }
}
