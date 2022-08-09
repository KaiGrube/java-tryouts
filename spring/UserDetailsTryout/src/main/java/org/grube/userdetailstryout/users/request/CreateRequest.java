package org.grube.userdetailstryout.users.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class CreateRequest {
    private String username;
    private String password;
    private String role;
    private Boolean isEnabled;

}