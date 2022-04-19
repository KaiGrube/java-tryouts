package org.grube.simplehttptryouts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SimpleHttpTryoutsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleHttpTryoutsApplication.class, args);
	}

}
