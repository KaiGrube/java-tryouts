package org.grube.registrationloginjwtreact.security.token;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jsonWebToken = request.getHeader("jwt");
        if (jsonWebToken == null) {
            log.error("No jwt token in headers.");
        } else {
            try {
                Claims claims = jwtProvider.getClaimsFromJsonWebToken(jsonWebToken);
                log.info("Claims:" + jwtProvider.getClaimsFromJsonWebToken(jsonWebToken));
                if (!claims.getExpiration().before(new Date())) {
                    Authentication authentication = jwtProvider.getAuthentication(claims.getSubject());
                    if (authentication.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.info("authenticated user");
                    }
                }
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("expired or invalid JWT token " + e.getMessage());
//                    response.getWriter().println(new JSONObject().put("exception", "expired or invalid JWT token " + e.getMessage()));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
