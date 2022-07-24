package org.grube.registrationloginjwtreact.login;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.registrationloginjwtreact.user.User;
import org.grube.registrationloginjwtreact.user.UserRole;
import org.grube.registrationloginjwtreact.user.UserService;
import org.grube.registrationloginjwtreact.security.token.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
//@RequestMapping(path = "api/v1")
//@CrossOrigin(origins = "http://${frontend.host}:${frontend.port}")
public class LoginController {

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = (User) userService.loadUserByUsername(username); // todo: rename username/email?!
        UserRole userRole = user.getUserRole();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jsonWebToken = jwtProvider.createJsonWebToken(username, userRole);
            Claims claims = jwtProvider.getClaimsFromJsonWebToken(jsonWebToken);
            log.info(String.format("Valid credentials, JWT Claims %s", claims));
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(jsonWebToken));
        } catch (BadCredentialsException e) {
            log.info(String.format("Bad credentials, username: %s, password: %s", username, password));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong.");
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}