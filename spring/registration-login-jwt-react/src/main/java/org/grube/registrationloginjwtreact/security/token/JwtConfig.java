package org.grube.registrationloginjwtreact.security.token;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtProvider jwtProvider;

    public JwtConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
