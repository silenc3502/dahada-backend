package com.dahada.backend.application.partners.service;

import com.dahada.backend.api.page.partners.form.PartnerSignUpForm;
import com.dahada.backend.application.partners.dto.PartnerSignUpRequest;
import com.dahada.backend.application.partners.entity.Partner;
import com.dahada.backend.application.partners.repository.PartnerSignUpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PartnerSignUpServiceImpl implements PartnerSignUpService {

    @Autowired
    private PartnerSignUpRepository partnerSignUpRepository;

    @Override
    public void partnerSignUp(PartnerSignUpForm partnerSignUpForm) {

        final Partner partner = partnerSignUpForm.toPartner();

        partnerSignUpRepository.save(partner);
    }
}
