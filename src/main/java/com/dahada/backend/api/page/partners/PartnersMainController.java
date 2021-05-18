package com.dahada.backend.api.page.partners;

import com.dahada.backend.application.calculation.entity.Calculation;
import com.dahada.backend.application.calculation.service.PartnerCalculationService;
import com.dahada.backend.application.configuration.resolvers.annotations.Authenticated;
import com.dahada.backend.application.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/partners")
public class PartnersMainController {

    // 일반 사용자(고객)과 파트너스(기사)가 상호간에 작업 매칭이 된 경우 정산을 잡을 수 있다.
    // 그러므로 우선은 수동으로 구성할 수 있게 만들어 놓도록 한다.
    // private PartnerCalculationService partnerCalculationService;

    @GetMapping
    public String index() {

        return "/partner/index";
    }

    @GetMapping("/calculationRegister")
    public String calculationRegister(Model model) throws Exception {
        model.addAttribute("calculation", new Calculation());

        return "/calculation/register";
    }

    @PostMapping("/calculationRegister")
    public String calculationRegisterForm(Calculation calculation, Model model) throws Exception {
        return "/calculation/register";
    }

    @GetMapping("/calculation")
    public String partnerCalculation (Model model) {
        log.info("partnerCalculation()");

        model.addAttribute("list", null);

        return "/partner/calculation";
    }
}
