package org.grube.registrationloginjwtreact.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
//@RequestMapping(path = "api/v1")
//@CrossOrigin(origins = "http://${frontend.host}:${frontend.port}")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    public List<User> users() {
        return userService.getUsers();
    }

    @GetMapping(path = "/user/{userId}")
    public MappingJacksonValue userDetails(@PathVariable(value = "userId") String userIdParam) {
        long userId = Long.parseLong(userIdParam);
        User user = userService.getUser(userId);

        // Dangerous!
        // todo: Seperate into two tables? Or at least specify what fields should be read (white list)

        String[] fieldsHiddenForUserRole = {
                "id",
                "password",
                "userRole",
                "locked",
                "enabled",
                "authorities",
                "accountNonExpired",
                "accountNonLocked",
                "credentialsNonExpired"
        };
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(fieldsHiddenForUserRole);
        FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
