package com.dahada.backend.api.page.partners;

import com.dahada.backend.api.page.partners.form.PartnerSignInForm;
import com.dahada.backend.api.page.partners.form.PartnerSignUpForm;
import com.dahada.backend.application.calculation.entity.Calculation;
import com.dahada.backend.application.calculation.service.PartnerCalculationService;
import com.dahada.backend.application.configuration.resolvers.annotations.Authenticated;
import com.dahada.backend.application.partners.service.PartnerSignInService;
import com.dahada.backend.application.partners.service.PartnerSignUpService;
import com.dahada.backend.application.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/partners")
public class PartnersMainController {

    // 일반 사용자(고객)과 파트너스(기사)가 상호간에 작업 매칭이 된 경우 정산을 잡을 수 있다.
    // 그러므로 우선은 수동으로 구성할 수 있게 만들어 놓도록 한다.
    @Autowired
    private PartnerCalculationService partnerCalculationService;

    @Autowired
    private PartnerSignUpService partnerSignUpService;

    @Autowired
    private PartnerSignInService partnerSignInService;

    @GetMapping
    public String index() {
        log.info("partnerIndex()");

        return "/partner/index";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {

        // 일단 구조 신경 안쓰고 돌아가게 흐름만 만듭니다.
        model.addAttribute("partnerSignUpForm", new PartnerSignUpForm());

        return "/partner/sign-up";
    }

    @PostMapping("/sign-up")
    public ModelAndView signUp(Model model, PartnerSignUpForm partnerSignUpForm) {

        model.addAttribute("partnerSignUpForm", partnerSignUpForm);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/partner/sign-up-detail");

        return modelAndView;
    }

    @PostMapping("/finish-sign-up")
    public ModelAndView finishSignUp(Model model, PartnerSignUpForm partnerSignUpForm) {

        log.info("partnerSignUpForm: " + partnerSignUpForm);

        partnerSignUpService.partnerSignUp(partnerSignUpForm);

        model.addAttribute("partnerSignUpForm", partnerSignUpForm);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/partner/finish-sign-up");

        return modelAndView;
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("partnerSignInForm", new PartnerSignInForm());

        return "/partner/sign-in";
    }

    @PostMapping("/sign-in")
    public String finishSignIn(Model model, PartnerSignInForm partnerSignInForm) {
        log.info("partnerSignInForm: " + partnerSignInForm);

        partnerSignInService.partnerSignIn(partnerSignInForm.toSignInRequest());

        return "redirect:/partners";
    }

    @GetMapping("/calculationRegister")
    public String calculationRegister(Model model) throws Exception {
        log.info("getCalculationRegister()");

        model.addAttribute("calculation", new Calculation());

        return "/calculation/register";
    }

    @PostMapping("/calculationRegister")
    public String calculationRegisterForm(Calculation calculation, Model model) throws Exception {
        log.info("postCalculationRegister()");

        partnerCalculationService.register(calculation);

        return "/partner/index";
    }

    @GetMapping("/calculation")
    public String partnerCalculation (Model model) throws Exception {
        log.info("partnerCalculation()");

        model.addAttribute("list", partnerCalculationService.list());

        return "/partner/calculation";
    }

    @GetMapping("/work-schedule")
    public String partnerWorkSchedule () {
        log.info("partnerWorkSchedule()");

        return "work/work-schedule";
    }

    @GetMapping("/partner-matching")
    public String partnerMatching () {
        log.info("partnerMatching()");

        return "work/partner-matching";
    }
}
