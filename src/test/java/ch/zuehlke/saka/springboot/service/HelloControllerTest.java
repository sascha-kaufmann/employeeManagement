package ch.zuehlke.saka.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getHello() throws Exception {
		HelloTO expectedBody = new HelloTO();
		expectedBody.setHelloText("Hello dear Sir.");

		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON));

		result
		   .andExpect(status().isOk())
		   .andExpect(content().json(objectMapper.writeValueAsString(expectedBody)));
	}

	@Test
	public void getHello_XML() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_XML))
		   .andExpect(status().isNotAcceptable());
	}
}