package org.grube.userdetailstryout.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationProvider authenticationProvider;

    public boolean authenticate(String username, String rawPassword) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, rawPassword);
        Authentication authentication = authenticationProvider.authenticate(token);
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info(String.format("Authenticated Principal=%s, Role=%s", authentication.getPrincipal(), authentication.getAuthorities()));
            return true;
        }
        return false;
    }
}
