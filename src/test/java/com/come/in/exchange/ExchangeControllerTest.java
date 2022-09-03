package com.come.in.exchange;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import com.come.in.user.User;
import com.come.in.user.UserService;
import com.google.gson.Gson;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ExchangeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ExchangeService exchangeService;
	
	private Gson gson = new Gson();
	
	protected MockHttpSession session;
	
	Exchange exchange1;
	Exchange exchange2;
	
	@BeforeAll
	public void setUpBeforeClass() throws Exception {
		session = new MockHttpSession();
		
		exchange1 = new Exchange();
		exchange1.set_id("exId1");
		exchange1.setUserId("userId1");
		exchange1.setMyR(3);
		exchange1.setExW(2);
		
		exchange2 = new Exchange();
		exchange2.set_id("exId2");
		exchange2.setUserId("userId2");
		exchange2.setMyW(2);
		exchange2.setExR(2);
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
	public void exchangeTest_WhenSessionExists() throws Exception {
		//Given
		session.setAttribute("loginUser", new User("userId1","userName1","userEmail1"));
		
		List<Exchange> exchanges = Arrays.asList(exchange1);
		
		when(exchangeService.getExchangeByUserId(((User)session.getAttribute("loginUser")).get_id())).thenReturn(exchanges);
		
		//When
		ResultActions resultActions = mockMvc.perform(get("/exchange").session(session));
		
		//Then
		resultActions
		.andExpect(status().isOk())
		.andExpect(model().attribute("exchangeList", hasSize(1)))
		.andExpect(model().attribute("exchangeList", hasItem(
				allOf(
						hasProperty("_id", is("exId1")),
						hasProperty("userId", is("userId1")),
						hasProperty("myR", is(3)),
						hasProperty("exW", is(2))
					)
				)))
		.andExpect(view().name("exchange/list"));
	}
	
	@Test
	public void exchangeTest_WhenSessionNotExists() throws Exception {
		//Given
		session = new MockHttpSession();
		
		//When
		ResultActions resultActions = mockMvc.perform(get("/exchange").session(session));
		
		//Then
		resultActions
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/main"));
	}
	
	@Test
	public void exchangeAddGetTest() throws Exception {
		//when
		ResultActions resultActions = mockMvc.perform(get("/exchange/add"));
		
		//Then
		resultActions.andExpect(status().isOk())
					.andExpect(view().name("exchange/add"));
	}
	
	@Test
	public void exchangeAddPostTest() throws Exception {
		//Given
		doNothing().when(exchangeService).insertExchange(exchange2);
		
		//when
		ResultActions resultActions = mockMvc.perform(post("/exchange/add"));
		
		//Then
		resultActions
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/exchange"));
	}
	
	@Test
	public void exchangeViewGetTest() throws Exception {
		//Given
		when(exchangeService.getExchange("exId1")).thenReturn(exchange1);
		
		//When
		ResultActions resultActions = mockMvc.perform(get("/exchange/view").param("_id", "exId1"));
		
		//Then
		resultActions
		.andExpect(status().isOk())
		.andExpect(model().attribute("exchange", allOf(
				hasProperty("_id", is("exId1")),
				hasProperty("userId", is("userId1")),
				hasProperty("myR", is(3)),
				hasProperty("exW", is(2))
			)
		))
		.andExpect(model().attribute("exchange", is(exchange1)))
		.andExpect(view().name("exchange/view"));
	}
	
	@Test
	public void exchangeDelPostTest() throws Exception {
		//Given
		doNothing().when(exchangeService).delExchange("");
		
		//when
		ResultActions resultActions = mockMvc.perform(post("/exchange/del"));
		
		//Then
		resultActions
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/exchange"));
	}
	
	@Test
	public void exchangeEditGetTest() throws Exception {
		//Given
		when(exchangeService.getExchange("exId1")).thenReturn(exchange1);
		
		//When
		ResultActions resultActions = mockMvc.perform(get("/exchange/edit").param("_id", "exId1"));
		
		//Then
		resultActions
		.andExpect(status().isOk())
		.andExpect(model().attribute("exchange", is(exchange1)))
		.andExpect(view().name("exchange/add"));
	}
	
	@Test
	public void exchangeMatchingGetTest() throws Exception {
		//Given
		when(exchangeService.getExchange("exId1")).thenReturn(exchange1);
		List<Exchange> matching = Arrays.asList(exchange2);
		when(exchangeService.selectMatching(exchange1)).thenReturn(matching);
		
		//When
		ResultActions resultActions = mockMvc.perform(get("/exchange/matching").param("_id", "exId1"));
		
		//Then
		resultActions
		.andExpect(status().isOk())
		.andExpect(model().attribute("matching", is(matching)))
		.andExpect(model().attribute("exchange", is(exchange1)))
		.andExpect(view().name("exchange/matching"));
	}
	
	@Test
	public void requestMatchingPostTest() throws Exception {
		//Given
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("fromExchangeUserId", "exId1");
		resultMap.put("toExchangeUserId", "exId2");
		resultMap.put("status", "RW");
		when(exchangeService.requestMatching("exId1", "exId2")).thenReturn(resultMap);
		
		//When
		ResultActions resultActions = mockMvc.perform(post("/exchange/requestMatching").param("fromMatchingId", "exId1").param("toMatchingId", "exId2"));
		
		//Then
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("from", resultMap.get("fromExchangeUserId"));
		data.put("to", resultMap.get("toExchangeUserId"));
		data.put("status", "RW");
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(content().string(gson.toJson(data)));
	}
	
	@Test
	public void acceptMatchingPostTest() throws Exception {
		//Given
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("fromExchangeUserId", "exId2");
		resultMap.put("toExchangeUserId", "exId1");
		resultMap.put("status", "SRS");
		when(exchangeService.acceptMatching("exId2", "exId1")).thenReturn(resultMap);
		
		//When
		ResultActions resultActions = mockMvc.perform(post("/exchange/acceptMatching").param("fromMatchingId", "exId2").param("toMatchingId", "exId1"));
		
		//Then
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("from", resultMap.get("fromExchangeUserId"));
		data.put("to", resultMap.get("toExchangeUserId"));
		data.put("status", "SRS");
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(content().string(gson.toJson(data)));
	}
}