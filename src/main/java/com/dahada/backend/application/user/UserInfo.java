package com.dahada.backend.application.user;

import com.dahada.backend.application.auth.service.UserDetails;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserInfo implements UserDetails {
    private String email;
    private String name;
    private String signature;
}
