package org.grube.userdetailstryout.users;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final UserService userService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userService.createUser("admin@admin.com", "Pass$123", "ADMIN", true);
            userService.createUser("user1@user.com", "user1", "USER", true);
            userService.createUser("user2@usere.com", "user2", "USER", true);
        };
    }
}
