package com.come.in.exchange;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import com.come.in.user.User;
import com.google.gson.Gson;

//@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest(ExchangeController.class)
@TestInstance(Lifecycle.PER_CLASS)
class ExchangeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	protected MockHttpSession session;
	
	@BeforeAll
	public void setUpBeforeClass() throws Exception {
		session = new MockHttpSession();
		session.setAttribute("loginUser", new User("607c335dc49e252c88301c77","name","email"));
	}

	@AfterAll
	public void tearDownAfterClass() throws Exception {
		session.clearAttributes();
	}

	//@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	public void selectExchange() throws Exception {
		mockMvc.perform(get("/exchange")
				.session(session)
				.contentType(MediaType.APPLICATION_JSON)
				//.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn()
				;
	}
	
	@Test
	public void getLogin() throws Exception {
		mockMvc.perform(get("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn()
				;
	}
	
	@Test
	public void postLogin() throws Exception {
		//Map<String, Object> param = new HashMap<String, Object>();
		//param.put("id", "607c335dc49e252c88301c77");
		
		//Gson gson = new Gson();
        //String content = gson.toJson(param);
        
		mockMvc.perform(post("/login")
				//.session(session)
				//.contentType(MediaType.APPLICATION_JSON)
				//.accept(MediaType.APPLICATION_JSON)
				.param("id", "607c335dc49e252c88301c77")
				//.params((MultiValueMap<String, Object>)param))
				//.content(content)
				)
				.andExpect(status().is3xxRedirection())
				.andDo(print())
				.andReturn()
				;
	}
}
