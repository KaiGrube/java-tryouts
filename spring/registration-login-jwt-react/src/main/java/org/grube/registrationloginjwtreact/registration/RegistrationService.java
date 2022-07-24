package org.grube.registrationloginjwtreact.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.registrationloginjwtreact.user.User;
import org.grube.registrationloginjwtreact.user.UserRole;
import org.grube.registrationloginjwtreact.user.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    //todo: create Exception Handler
    public String register(RegistrationRequest registrationRequest) {
        String requestedEmail = registrationRequest.getEmail();

        // todo: validate email by @Email annotation
//        boolean isValidEmail = emailValidator.test(requestedEmail);
//        if (!isValidEmail) {
//            log.info(String.format("Email %s is not valid.", requestedEmail));
////            throw new IllegalArgumentException("requestedEmail not valid");
//        }

//        try {
//            userService.loadUserByUsername(requestedEmail);
//            throw new RuntimeException(requestedEmail);
//        } catch (UsernameNotFoundException e) {
//            log.info(String.format("E-mail does not exist. Registering new user %s", requestedEmail));
//        }

        User userToRegister = new User(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                requestedEmail,
                registrationRequest.getPassword(),
                UserRole.USER);
        String user = userService.signUp(userToRegister);
        return user;
    }
}
