package com.dahada.backend.application.partners.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor
public class PartnerSignUpRequest {

    private final String email;
    private final String id;
    private final String password;
    private final String passwordConfirm;

    private final String companyRegistrationNumber;
    private final String name;
    private final String residentRegistrationNumber;

    private final String businessName;
    private final String companyName;
    private final String representativeName;
    private final String phoneNumber;
    private final String businessCondition;
    private final String businessCategory;

    private final String city;
    private final String street;
    private final String addressDetail;
    private final String zipcode;

    private final String bankName;
    private final String depositorName;
    private final String accountNumber;
}
