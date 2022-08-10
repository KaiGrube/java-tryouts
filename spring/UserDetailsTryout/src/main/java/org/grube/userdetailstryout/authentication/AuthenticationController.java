package org.grube.userdetailstryout.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.userdetailstryout.ApiException;
import org.grube.userdetailstryout.users.User;
import org.grube.userdetailstryout.users.UserService;
import org.grube.userdetailstryout.authentication.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/authentication")
public class AuthenticationController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    // todo: Rename SignupRequest to something that is applicable for login and signup
    public ResponseEntity<Object> loginUser(@RequestBody SignupRequest loginRequest) {
        String username = loginRequest.getUsername();
        String rawPassword = loginRequest.getPassword();
        Boolean isAuthenticated = authenticationService.authenticate(username, rawPassword);
        return ResponseEntity.ok().body(isAuthenticated);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body("cleared context");
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@Valid @RequestBody SignupRequest signupRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ApiException apiException = new ApiException("Validation error.");
            bindingResult.getFieldErrors().forEach(
                    error -> apiException.addSubError(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(apiException);
        }

        Optional<User> signedUpUser = userService.createUser(
                signupRequest.getUsername(),
                signupRequest.getPassword(),
                "USER",
                true
        );
        if (signedUpUser.isPresent()) return ResponseEntity.ok().body(signedUpUser.get().getId());
        return ResponseEntity.ok().body("signup");
    }
}
