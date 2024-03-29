package org.grube.userdetailstryout;

import lombok.RequiredArgsConstructor;
import org.grube.userdetailstryout.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
    private final UserService userService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userService.createUser("admin@admin.com", "Pass123$", "ADMIN", true);
            userService.createUser("user1@user.com", "user1", "USER", true);
            userService.createUser("user2@user.com", "user2", "USER", true);
        };
    }
}
