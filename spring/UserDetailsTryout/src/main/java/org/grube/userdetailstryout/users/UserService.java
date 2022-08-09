package org.grube.userdetailstryout.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public Optional<User> createUser(String username, String rawPassword, String role, boolean isEnabled) {
        boolean usernameExists = userRepository.findByUsername(username).isPresent();
        if (usernameExists) {
            String message = String.format("Username '%s' already exists", username);
            throw new RuntimeException(message);
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User userToCreate = new User(username, encodedPassword, role, isEnabled);
        return Optional.of(userRepository.save(userToCreate));
    }

    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id).isPresent();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
