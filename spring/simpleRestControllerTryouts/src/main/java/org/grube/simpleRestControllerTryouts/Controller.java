package org.grube.simpleRestControllerTryouts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/getString")
    public String getRequestResponseAsString() {
        return "Hello World:-)";
    }
    @GetMapping(value = "/getJson")
    public MyEntity getJson(@RequestParam("name") String name,
                            @RequestParam("age") int age) {
        return new MyEntity(name, age);
    }


}
