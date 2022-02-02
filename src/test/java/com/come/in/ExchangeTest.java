package com.come.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.come.in.exchange.Exchange;
import com.come.in.exchange.ExchangeController;
import com.come.in.exchange.ExchangeService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class ExchangeTest extends SpringMockMvcTestSupport {

	private Exchange exchange1;
	private Exchange exchange2;
	
	@Autowired
	private ExchangeController exchangeController;
	
	@Autowired
    private ExchangeService exchangeService;
	
	@Autowired
	private MockMvc mvc;
	
	@BeforeAll
	public void beforeAll() {
		exchangeController = new ExchangeController();
		
		exchange1 = new Exchange();
		exchange1.set_id("exId1");
		exchange1.setUserId("userId1");
		exchange1.setMyR(3);
		exchange1.setExW(2);
		
		exchange2 = new Exchange();
		exchange2.set_id("exId2");
		exchange2.setUserId("userId2");
		exchange2.setMyW(1);
		exchange2.setExR(2);
	}
	
	//@Test
	//void test() {
	//	fail("Not yet implemented");
	//}
	
	@Test
	public void example() throws Exception {
		this.mvc.perform(get("/main")).andDo(print())
			.andExpect(status().is(HttpStatus.OK.value()));
	}

	//@Test
	public void insertExchange() {
		exchangeController.exchangeAddPost(null, exchange1, null, null);
		assertEquals(exchange1, exchangeService.getExchangeByUserId("userId1").get(0));
	}
	
	//@Test
	public void whenRequestMatching() {
		String _id = "61a8f95d780d9b1ddebbe01c";
		assertEquals(_id, "61a8f95d780d9b1ddebbe01c");
	}
	
	@AfterAll
	public void afterAll() {
		System.out.println("끝");
	}
}
