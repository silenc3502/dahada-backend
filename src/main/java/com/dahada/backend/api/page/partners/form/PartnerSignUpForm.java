package com.dahada.backend.api.page.partners.form;

import com.dahada.backend.application.partners.entity.Partner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PartnerSignUpForm {
    private String email;
    private String id;
    private String password;
    private String passwordConfirm;

    private String companyRegistrationNumber;
    private String name;
    private String residentRegistrationNumber;

    private String businessName;
    private String companyName;
    private String representativeName;
    private String phoneNumber;
    private String businessCondition;
    private String businessCategory;

    private String city;
    private String street;
    private String addressDetail;
    private String zipcode;

    private String bankName;
    private String depositorName;
    private String accountNumber;

    public Partner toPartner() {

        return new Partner(
                email,
                city, street, addressDetail, zipcode,
                companyRegistrationNumber, businessName, companyName, representativeName, phoneNumber,
                businessCondition, businessCategory, bankName, depositorName, accountNumber
        );
    }
}
