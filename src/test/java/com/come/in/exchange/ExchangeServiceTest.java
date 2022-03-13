package com.come.in.exchange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ExchangeServiceTest {

	private Exchange exchange1;
	private Exchange exchange2;
	
	@Autowired
    private ExchangeService exchangeService;
	
	@MockBean
    private ExchangeRepository exchangeRepository;
	
	@BeforeAll
	public void beforeAll() throws Exception {
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
		
		//exchangeService.insertExchange(exchange1);
		//exchangeService.insertExchange(exchange2);
	}
	
	@AfterAll
	public void afterAll() throws Exception {
		//exchangeService.delExchange("exId1");
		//exchangeService.delExchange("exId2");
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void getExchangeTest() {
		//Given
		when(exchangeRepository.findById("exId1")).thenReturn(Optional.of(exchange1));
		
		//When
		Exchange exchange = exchangeService.getExchange("exId1");
		
		//Then
		verify(exchangeRepository, times(1)).findById("exId1");
		assertThat(exchange).isEqualTo(exchange1);
	}
	
	@Test
	void getExchangeByUserIdTest() {
		//Given
		List<Exchange> exchanges = Arrays.asList(exchange1);
		when(exchangeRepository.findByUserId("userId1")).thenReturn(exchanges);
		
		//When
		List<Exchange> exchangeList = exchangeService.getExchangeByUserId("userId1");
		
		//Then
		verify(exchangeRepository, times(1)).findByUserId("userId1");
		assertThat(exchanges).isEqualTo(exchangeList);
	}
	
	@Test
	void selectMatchingTest_myRexW() {
		//Given
		List<Exchange> exchanges = Arrays.asList(exchange2);
		when(exchangeRepository.findMatchingByExchangeRW(exchange1.getMyR(), exchange1.getExW())).thenReturn(exchanges);
		
		//When
		List<Exchange> exchangeList = exchangeService.selectMatching(exchange1);
		
		//Then
		verify(exchangeRepository, times(1)).findMatchingByExchangeRW(exchange1.getMyR(), exchange1.getExW());
		assertThat(exchanges).isEqualTo(exchangeList);
	}
	
	@Test
	void selectMatchingTest_myWexR() {
		//Given
		List<Exchange> exchanges = Arrays.asList(exchange1);
		when(exchangeRepository.findMatchingByExchangeWR(exchange2.getMyW(), exchange2.getExR())).thenReturn(exchanges);
		
		//When
		List<Exchange> exchangeList = exchangeService.selectMatching(exchange2);
		
		//Then
		verify(exchangeRepository, times(1)).findMatchingByExchangeWR(exchange2.getMyW(), exchange2.getExR());
		assertThat(exchanges).isEqualTo(exchangeList);
	}
	
	@Test
	void requestMatchingTest() {
		//Given
		when(exchangeRepository.findById("exId1")).thenReturn(Optional.of(exchange1));
		when(exchangeRepository.findById("exId2")).thenReturn(Optional.of(exchange2));
		
		//When
		Map<String, Object> resultMap = exchangeService.requestMatching("exId1", "exId2");
		
		//Then
		verify(exchangeRepository, times(1)).findById("exId1");
		verify(exchangeRepository, times(1)).findById("exId2");
		verify(exchangeRepository, times(1)).save(exchange1);
		verify(exchangeRepository, times(1)).save(exchange2);
		assertThat(exchange1.getMatchingStatus()).isEqualTo("R");
		assertThat(exchange2.getMatchingStatus()).isEqualTo("W");
		assertThat(resultMap.get("fromExchangeUserId")).isEqualTo(exchange1.getUserId());
		assertThat(resultMap.get("toExchangeUserId")).isEqualTo(exchange2.getUserId());
		assertThat(resultMap.get("status")).isEqualTo("RW");
	}
	
	@Test
	void updateMatchingStatusTest() {
		//Given
		when(exchangeRepository.findById("exId1")).thenReturn(Optional.of(exchange1));
		
		//When
		exchangeService.updateMatchingStatus("exId1", "XXX");
		
		//Then
		verify(exchangeRepository, times(1)).findById("exId1");
		assertThat(exchange1.getMatchingStatus()).isEqualTo("XXX");
	}
	
	@Test
	void acceptMatchingTest() {
		//Given
		when(exchangeRepository.findById("exId2")).thenReturn(Optional.of(exchange2));
		when(exchangeRepository.findById("exId1")).thenReturn(Optional.of(exchange1));
		//doNothing().when(exchangeRepository).save(exchange2);
		//doNothing().when(exchangeRepository).save(exchange1);
		
		//When
		Map<String, Object> resultMap = exchangeService.acceptMatching("exId2", "exId1");
		
		//Then
		verify(exchangeRepository, times(1)).findById("exId2");
		verify(exchangeRepository, times(1)).findById("exId1");
		verify(exchangeRepository, times(1)).save(exchange2);
		verify(exchangeRepository, times(1)).save(exchange1);
		assertThat(exchange2.getMatchingStatus()).isEqualTo("S");
		assertThat(exchange1.getMatchingStatus()).isEqualTo("RS");
		assertThat(resultMap.get("fromExchangeUserId")).isEqualTo(exchange2.getUserId());
		assertThat(resultMap.get("toExchangeUserId")).isEqualTo(exchange1.getUserId());
		assertThat(resultMap.get("status")).isEqualTo("SRS");
	}
}
