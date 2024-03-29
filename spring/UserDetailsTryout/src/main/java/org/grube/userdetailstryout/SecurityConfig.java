package org.grube.userdetailstryout;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.userdetailstryout.authentication.CustomUserDetailsService;
import org.grube.userdetailstryout.authentication.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/authentication/signup",
                        "/authentication/login",
                        "/authentication/logout"
                )
                .permitAll()
                .antMatchers(
                        "/users"
                )
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/admins")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
//                .formLogin()
//                .loginProcessingUrl("/authentication/login")
//                .failureHandler((request, response, exception) -> log.error("authentication failed."))
//                .successHandler(new CustomAuthenticationSuccessHandler())
//                .and()
//                .logout()
//                .logoutUrl("/authentication/logout")
//                .logoutSuccessUrl("/logoutSuccess")
//                .deleteCookies("JSESSIONID")
//                .clearAuthentication(true)
//                .and()
                .csrf().disable();
    }
}