package org.grube.userdetailstryout;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BasicController {

    @GetMapping("/")
    public String root(){
        return "This is root. Should be visible without being logged in.";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
