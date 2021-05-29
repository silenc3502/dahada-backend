package com.dahada.backend.application.partners.repository;

import com.dahada.backend.application.partners.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerSignUpRepository extends JpaRepository<Partner, Long> {

}