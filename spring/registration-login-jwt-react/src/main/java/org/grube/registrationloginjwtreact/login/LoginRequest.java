package org.grube.registrationloginjwtreact.login;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class LoginRequest {
    private final String username;
    private final String password;
}
