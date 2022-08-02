package org.grube.userdetailstryout.users;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User user1 = new User("admin", passwordEncoder.encode("admin"), "ADMIN");
            User user2 = new User("user", passwordEncoder.encode("user"), "USER");
            userRepository.saveAll(List.of(user1, user2));
        };
    }
}
