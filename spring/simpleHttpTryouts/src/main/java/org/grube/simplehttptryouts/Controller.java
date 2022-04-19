package org.grube.simplehttptryouts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String repondWithString() {
        System.out.println("Map :-)");
        return "Hello, I am a string";
    }
}
