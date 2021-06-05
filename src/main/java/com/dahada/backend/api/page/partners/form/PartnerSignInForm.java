package com.dahada.backend.api.page.partners.form;

import com.dahada.backend.application.partners.dto.PartnerSignInRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PartnerSignInForm {
    private String email;
    private String password;

    public PartnerSignInRequest toSignInRequest() {
        return new PartnerSignInRequest(email, password);
    }
}
