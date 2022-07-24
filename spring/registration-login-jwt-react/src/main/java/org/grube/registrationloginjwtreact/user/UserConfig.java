package org.grube.registrationloginjwtreact.user;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserConfig {

    private final UserService userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {

            // todo: User must not specify role!
            User admin = new User(
                    "Anton",
                    "Administri",
                    "anton@admin.com",
                    "admin",
                    UserRole.ADMIN);

            User user = new User(
                    "Ulf",
                    "Useri",
                    "ulf@user.com",
                    "user",
                    UserRole.USER);


//            appUserRepository.saveAll(List.of(admin, user));
            userService.signUp(admin);
            userService.signUp(user);
        };
    }
}
