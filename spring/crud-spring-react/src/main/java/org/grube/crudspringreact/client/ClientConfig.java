package org.grube.crudspringreact.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {
    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
        return args -> {
            Client client1 = new Client("Anton", "anton@earth.com");
            Client client2 = new Client("Berta", "berta@berlin.com");
            Client client3 = new Client("Zaphod", "zaphod@earth.com");
            clientRepository.saveAll(List.of(client1, client2, client3));
        };
    }
}
