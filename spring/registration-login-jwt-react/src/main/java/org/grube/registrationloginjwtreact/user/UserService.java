package org.grube.registrationloginjwtreact.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUp(User userToSignUp) {
        boolean userExists = userRepository.findByEmail(userToSignUp.getEmail()).isPresent();
        if (userExists) throw new IllegalStateException("email already exists");
        String encodedPassword = bCryptPasswordEncoder.encode(userToSignUp.getPassword()); // todo: encrypt password again later
        userToSignUp.setPassword(encodedPassword);
        User signedUpUser = userRepository.save(userToSignUp);
        log.info(String.format("New user signed up: %s", signedUpUser));
        return signedUpUser.toString();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(long userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
