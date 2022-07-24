package org.grube.registrationloginjwtreact.registration;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    @Size(min = 2, message = "firstName must have at least 2 characters")
    private final String firstName;
    @Size(min = 2, message = "lastName must have at least 2 characters")
    private final String lastName;
    @Email(message = "email must be a valid email address")
    private final String email;
    private final String password;
}
