package org.grube.userdetailstryout.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.userdetailstryout.ApiException;
import org.grube.userdetailstryout.users.requests.CreateRequest;
import org.grube.userdetailstryout.users.requests.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String user(){
        return "This is users page. Should only be visible for logged in users with role 'USER'.";
    }

    @GetMapping("/admins")
    public String admin(){
        return "This is users page. Should only be visible for logged in users with role 'ADMIN'.";
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ApiException apiException = new ApiException("Validation error.");
            bindingResult.getFieldErrors().forEach(error ->
                    apiException.addSubError(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(apiException);
        }
        log.info(signupRequest.toString());

        Optional<User> signedUpUser = userService.createUser(
                signupRequest.getUsername(),
                signupRequest.getPassword(),
                "USER",
                true
        );
        if (signedUpUser.isPresent()) return ResponseEntity.ok().body(signedUpUser.get().getId());
        return ResponseEntity.ok().body("signup");
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody CreateRequest createRequest) {
        log.info(createRequest.toString());
        Optional<User> createdUser = userService.createUser(
            createRequest.getUsername(),
            createRequest.getPassword(),
            createRequest.getRole(),
            createRequest.getIsEnabled()
        );

        if (createdUser.isPresent()) {
            ApiException apiException = new ApiException("User already exists.");
            log.info(apiException.toString());
            return ResponseEntity.ok().body(apiException);
        } else {
            return ResponseEntity.badRequest().body(createdUser.get().getId());
        }
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        boolean successful = userService.deleteUser(id);
        if (successful) {
            return String.format("User [id=%d] deleted.", id);
        } else {
            return String.format("Could not delete User [id=%d]", id);
        }
    }
}
