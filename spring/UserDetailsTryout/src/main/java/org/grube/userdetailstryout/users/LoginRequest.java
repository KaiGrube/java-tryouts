package org.grube.userdetailstryout.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class LoginRequest {
    private final String username;
    private final String password;
}