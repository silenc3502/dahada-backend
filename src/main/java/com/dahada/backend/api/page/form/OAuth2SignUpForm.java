package com.dahada.backend.api.page.form;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OAuth2SignUpForm {
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuth2SignUpForm(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
