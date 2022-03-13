package com.come.in;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.come.in.user.User;
import com.come.in.user.UserService;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	private Optional<User> user;
	
	@BeforeAll
	public void setUpBeforeClass() throws Exception {
		user = Optional.of(new User());
		user.get().set_id("testId");
		user.get().setEmail("test@test.com");
		user.get().setName("testName");
	}

	@AfterAll
	public void tearDownAfterClass() throws Exception {
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
		//Given
		when(userService.getUser("testId")).thenReturn(user);
		
		//When
		ResultActions resultActions = mockMvc.perform(get("/login"));
		
		//Then
		resultActions.andExpect(status().isOk())
					.andExpect(view().name("login"));
	}
	
	@Test
	public void LoginPostTest_WhenUserIsNotPresent() throws Exception {
		//Given
		Optional<User> user = Optional.ofNullable(null);
		when(userService.getUser("testId")).thenReturn(user);
		
		//When
		ResultActions resultActions = mockMvc.perform(post("/login").with(csrf()).param("id", "testId"));
		
		//Then
		//resultActions.andExpect(status().is3xxRedirection())//302
		resultActions.andExpect(status().isFound())
		.andExpect(redirectedUrl("/login"));
	}
	
	@Test
	public void LoginPostTest_WhenUserIsPresent() throws Exception {
		//Given
		when(userService.getUser("testId")).thenReturn(user);
		
		//When
		ResultActions resultActions = mockMvc.perform(post("/login").with(csrf()).param("id", "testId"));
		
		//Then
		resultActions.andExpect(status().isFound())
		.andExpect(redirectedUrl("/main"))
		.andExpect(request().sessionAttribute("loginUser", user.get()));
	}
}
