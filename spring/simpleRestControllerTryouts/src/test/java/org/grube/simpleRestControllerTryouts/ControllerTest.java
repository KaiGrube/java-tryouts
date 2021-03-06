package org.grube.simpleRestControllerTryouts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
	}
	@Test
	void getRequestResponseAsString_test() throws Exception {
		mockMvc.perform(get("/getString"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(":-)")))
				.andExpect(content().string("Hello World:-)"));
	}
	@Test
	void getJson_test() throws Exception {
		String expectedResponse = 	"""
									{"name":"myString./-:","age":42}
									""";
		mockMvc.perform(get("http://localhost:8080/getJson?name={name}&age={age}", "myString./-:", 42))
				.andExpect(content().json(expectedResponse));
	}
}
