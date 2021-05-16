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
        form.setCurrentStep("step1");
        session.setAttribute("reservationForm", form);
        log.debug("step1 - {}", form);
        return "redirect:/";
    }

    @PostMapping("/reservation-step2")
    public String reservation2(@RequestBody ReservationForm form) {
        form.setCurrentStep("step2");
        session.setAttribute("reservationForm", form);
        log.debug("step2 - {}", form);
        return "redirect:/";
    }

    @PostMapping("/reservation-step3")
    public String reservation3(@RequestBody ReservationForm form) {
        final ReservationForm prev = (ReservationForm) session.getAttribute("reservationForm");
        form.merge(prev);
        form.setCurrentStep("step3");
        session.setAttribute("reservationForm", form);
        log.debug("step3 - {}", form);
        return "redirect:/";
    }
}
