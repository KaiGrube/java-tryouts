import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RequiredArgsConstructor
public class UserControllerTests {

    private final MockMvc mockMvc;

    @Test
    void createUser() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "user");
        requestBody.put("password", "user_password");
        requestBody.put("role", "USER");
        requestBody.put("isEnabled", "true");

        MvcResult mvcResult = mockMvc.perform(post("/users")
            .content(requestBody.toString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("user"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.subtitle").value("test_subtitle"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.isbn13").value("1234567890128"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("42.42"))
            .andReturn();
    }
}
