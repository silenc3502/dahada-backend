package com.dahada.backend.application.partners.repository;

import com.dahada.backend.application.partners.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query("SELECT a FROM Partner a WHERE a.email = :email")
    Optional<Partner> findByEmail(String email);
}