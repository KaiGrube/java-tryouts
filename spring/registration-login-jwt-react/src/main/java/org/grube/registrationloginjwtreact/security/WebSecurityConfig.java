package org.grube.registrationloginjwtreact.security;

import lombok.RequiredArgsConstructor;
import org.grube.registrationloginjwtreact.security.token.JwtConfig;
import org.grube.registrationloginjwtreact.security.token.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = false)
//@EnableGlobalMethodSecurity(prePostEnabled = true) // todo: What is this?
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/users")
            .authenticated()
            .and()
            .authorizeRequests()
            .antMatchers("/registration", "/login", "/logout")
            .permitAll()
            .and()
            .apply(new JwtConfig(jwtProvider))
            .and()
            .logout();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}





