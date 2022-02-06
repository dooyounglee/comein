package com.come.in.exchange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ExchangeServiceTest {

	private Exchange exchange1;
	private Exchange exchange2;
	
	@Autowired
    private ExchangeService exchangeService;
	
	@BeforeAll
	public void beforeAll() {
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
		
		exchangeService.insertExchange(exchange1);
		exchangeService.insertExchange(exchange2);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void insertExchange() {
		exchangeService.insertExchange(exchange1);
		Exchange _exchange = exchangeService.getExchange("exId1");
		assertEquals(exchange1, _exchange);
	}
	
	@Test
	public void givenExchangeInMatchingList() {
		exchangeService.insertExchange(exchange2);
		
		List<Exchange> matching = exchangeService.selectMatching(exchange1);
		assertThat(matching).contains(exchange2);
	}
	
	@Test
	public void requestMatching() {
		Map<String, Object> resultMap = exchangeService.requestMatching("exId1", "exId2");
		
		assertEquals(exchangeService.getExchange("exId1").getMatchingId(),"exId2");
		assertEquals(exchangeService.getExchange("exId1").getMatchingStatus(),"R");
		assertEquals(exchangeService.getExchange("exId2").getMatchingId(),"exId1");
		assertEquals(exchangeService.getExchange("exId2").getMatchingStatus(),"W");
		
		if(resultMap != null) {
			assertEquals(resultMap.get("fromExchangeUserId"),"userId1");
			assertEquals(resultMap.get("toExchangeUserId"),"userId2");
			assertEquals(resultMap.get("status"),"RW");
		}
	}
	
	@Test
	public void acceptMatching() {
		Map<String, Object> resultMap = exchangeService.acceptMatching("exId1", "exId2");
		
		assertEquals(exchangeService.getExchange("exId1").getMatchingStatus(),"S");
		assertEquals(exchangeService.getExchange("exId2").getMatchingStatus(),"RS");
		
		if(resultMap != null) {
			assertEquals(resultMap.get("fromExchangeUserId"),"userId1");
			assertEquals(resultMap.get("toExchangeUserId"),"userId2");
			assertEquals(resultMap.get("status"),"SRS");
		}
	}
	
	@AfterAll
	public void afterAll() {
		exchangeService.delExchange("exId1");
		exchangeService.delExchange("exId2");
	}
}
