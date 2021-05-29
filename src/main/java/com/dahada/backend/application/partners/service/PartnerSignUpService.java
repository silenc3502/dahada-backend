package com.dahada.backend.application.partners.service;

import com.dahada.backend.api.page.partners.form.PartnerSignUpForm;
import com.dahada.backend.application.partners.dto.PartnerSignUpRequest;

public interface PartnerSignUpService {

    void partnerSignUp(PartnerSignUpForm partnerSignUpForm);
}
