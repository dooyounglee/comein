package com.come.in;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.come.in.user.User;
import com.come.in.user.UserService;

import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest
//@WebAppConfiguration
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	//@Test
	@Disabled
	void test() {
		fail("Not yet implemented");
	}

	@Test
	public void mainTest() throws Exception {
		//when
		ResultActions resultActions = mockMvc.perform(get("/main"));
		
		//then
		resultActions.andExpect(status().isOk())
					.andExpect(view().name("main"));
	}
	
	@Test
	public void LoginGetTest() throws Exception {
		//When
		ResultActions resultActions = mockMvc.perform(get("/main"));
		
		//Then
		resultActions.andExpect(status().isOk())
					.andExpect(view().name("login"));
	}
	
	@Test
	public void LoginPostTest_WhenUserIsPresent() throws Exception {
		//Given
		Optional<User> user = Optional.ofNullable(null);
		when(userService.getUser("testId")).thenReturn(user);
		
		//When
		//Map<String, Object> paramMap = new HashMap<>();
		//paramMap.put("id", "testId");
		ResultActions resultActions = mockMvc.perform(post("/login").with(csrf()).param("id", "testId"));
		
		//Then
		//resultActions.andExpect(status().is3xxRedirection())//302
		resultActions.andExpect(status().isFound())
		.andExpect(redirectedUrl("/login"));
	}
	
	@Test
	public void LoginPostTest_WhenUserIsNotPresent() throws Exception {
		//Given
		Optional<User> user = Optional.of(new User());
		user.get().set_id("testId");
		user.get().setEmail("test@test.com");
		user.get().setName("testName");
		when(userService.getUser("testId")).thenReturn(user);
		
		//When
		//Map<String, Object> paramMap = new HashMap<>();
		//paramMap.put("id", "testId");
		ResultActions resultActions = mockMvc.perform(post("/main").with(csrf()).param("id", "testId"));
		
		//Then
		resultActions.andExpect(status().isOk())
		.andExpect(redirectedUrl("/main"));
	}
}
