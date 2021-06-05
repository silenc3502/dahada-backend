package com.dahada.backend.application.partners.service;

import com.dahada.backend.api.page.partners.form.PartnerSignInForm;
import com.dahada.backend.application.partners.dto.PartnerSignInRequest;

public interface PartnerSignInService {
    void partnerSignIn(PartnerSignInRequest partnerSignInRequest);
}
