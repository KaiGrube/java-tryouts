package org.grube.registrationloginjwtreact.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@AllArgsConstructor
@Slf4j
@RestController
//@Validated // if annotated with @Validated, bindingResults won't be accessible
//@RequestMapping(path = "api/v1")
//@CrossOrigin(origins = "http://${frontend.host}:${frontend.port}")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(path = "/register")
    // If you use @Valid annotation on your OrderDetailPO you have some options to handle an invalid request.
    // Option 1: You use BindingResult in your method signature like you did in your code.
    // Now you are able to use if (bindingResult.hasErrors()) and if it is true you are free to do anything you want.
    // You can throw your own exceptions, return a custom message or just go on with your code. Thats your decision.
    // And you don't have to catch any exception.
    // Option 2:
    // Delete BindingResult in your method signature. This means that Spring will automatically return a BadRequest
    // to the client. It will respond with an org.springframework.web.bind.MethodArgumentNotValidException with all
    // the information about the invalid request. In this case you will not enter your method. Same as option 1,
    // no exception must be caught.
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequest request) { // Important
        String response = registrationService.register(request);
        return ResponseEntity.ok().body(response);
    }
}
