package org.grube.registrationloginjwtreact.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.grube.registrationloginjwtreact.user.UserRole;
import org.grube.registrationloginjwtreact.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Autowired
    private UserService userService;

    @Value("${jwt.jwtSecretSigningKey:defaultJwtSecretSigningKeyValue}")
    private String jwtSecretSigningKey;

    @Value("${jwt.expirationTime:5000}")
    private long expirationTime;

    public String createJsonWebToken(String username, UserRole role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role); // use Role object instead of String
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtSecretSigningKey)
                .compact();
    }

    public Claims getClaimsFromJsonWebToken(String jsonWebToken) {
        return Jwts.parser()
                .setSigningKey(jwtSecretSigningKey)
                .parseClaimsJws(jsonWebToken)
                .getBody();
    }

    public Authentication getAuthentication(String username) {
        log.info("~~~appUserService: " + userService);
        UserDetails userDetails = userService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getAuthorities());
    }
}
