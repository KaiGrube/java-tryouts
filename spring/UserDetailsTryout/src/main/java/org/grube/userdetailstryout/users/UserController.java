package org.grube.userdetailstryout.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.userdetailstryout.ApiException;
import org.grube.userdetailstryout.users.request.CreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
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
