package com.dahada.backend.application.partners.service;

import com.dahada.backend.api.page.partners.form.PartnerSignInForm;
import com.dahada.backend.application.partners.dto.PartnerSignInRequest;
import com.dahada.backend.application.partners.entity.Partner;
import com.dahada.backend.application.partners.exception.IncorrectPasswordException;
import com.dahada.backend.application.partners.exception.UserNotFoundException;
import com.dahada.backend.application.partners.repository.PartnerRepository;
import com.dahada.backend.application.session.SessionService;
import com.dahada.backend.application.session.dto.SaveUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PartnerSignInServiceImpl implements PartnerSignInService {

    @Autowired
    private PartnerRepository repository;
    // private final SessionService sessionService;

    public PartnerSignInServiceImpl(PartnerRepository repository) {
        this.repository = repository;
        // this.sessionService = sessionService;
    }

    @Override
    public void partnerSignIn(PartnerSignInRequest request) {
        log.info("partnerSignIn()");
        final String email = request.getEmail();
        final Optional<Partner> maybePartner = repository.findByEmail(email);
        if (maybePartner.isPresent()) {
            final Partner partner = maybePartner.get();
            if (!partner.isRightPassword(request.getPassword())) {
                throw new IncorrectPasswordException("패스워드가 잘못됨");
            }
            // sessionService.save(new SaveUserRequest(partner.getEmail()));
            return;
        }
        throw new UserNotFoundException("가입된 사용자가 아님");
    }
}
