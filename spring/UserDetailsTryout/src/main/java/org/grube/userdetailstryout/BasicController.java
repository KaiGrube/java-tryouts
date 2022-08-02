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

    @GetMapping("/user")
    public String user(){
        return "This is user page. Should only be visible for logged in users with role 'USER'.";
    }

    @GetMapping("/admin")
    public String admin(){
        return "This is user page. Should only be visible for logged in users with role 'ADMIN'.";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(){
        return "Successfully logged out.";
    }
}
