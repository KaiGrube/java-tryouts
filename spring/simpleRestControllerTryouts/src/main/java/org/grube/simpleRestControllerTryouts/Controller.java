package org.grube.simpleRestControllerTryouts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/helloWorld")
    public String helloWorld() {
        return "Hello World:-)";
    }

    // GET http://localhost:8080/getRequestParams?stringParam=myString.%2F-%3A&intParam=42
    // should return url-decoded JSON string
    // {
    //  "stringParam": "myString./-:",
    //  "intParam": 42
    // }
    @GetMapping(value = "/getRequestParams")
    public MyEntity showRequestParams1(@RequestParam("stringParam") String stringParam,
                                       @RequestParam("intParam") int intParam) {
//        String message = String.format("stringParam=%s\nintParam=%d\n", stringParam, intParam);
        return new MyEntity(stringParam, intParam);
    }


}
