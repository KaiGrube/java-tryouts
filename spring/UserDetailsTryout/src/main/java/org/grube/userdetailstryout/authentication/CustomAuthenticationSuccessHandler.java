package org.grube.userdetailstryout.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        HttpSession session = request.getSession(false);
//        Instant instant = Instant.ofEpochMilli(((long) session.getMaxInactiveInterval() * 1000));
//        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        log.info(String.format("Session Timeout %s", dateTime.format(formatter)));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info(String.format("User logged in [username=%s, authorities=%s]", userDetails.getUsername(), userDetails.getAuthorities()));
    }
}
