package com.come.in.exchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Rollback(true)
public class ExchangeRepositoryTest {

	//@Autowired
    //private TestEntityManager testEntityManager;
	
	@Autowired
    private ExchangeRepository exchangeRepository;
	
	private Exchange exchange1;
	private Exchange exchange2;
	
	@BeforeAll
	public void beforeAll() throws Exception {
		exchange1 = new Exchange();
		exchange1.set_id("exId1");
		exchange1.setUserId("userId1");
		exchange1.setMyR(3);
		exchange1.setExW(2);
		exchangeRepository.save(exchange1);
		
		exchange2 = new Exchange();
		exchange2.set_id("exId2");
		exchange2.setUserId("userId2");
		exchange2.setMyW(2);
		exchange2.setExR(2);
		exchangeRepository.save(exchange2);
	}
	
	@AfterAll
	public void afterAll() throws Exception {
		exchangeRepository.delete(exchange1);
		exchangeRepository.delete(exchange2);
	}
	
	@Test
	public void findByUserIdTest() {
		//Given
		
		//When
		List<Exchange> exchangeList = exchangeRepository.findByUserId("userId2");
		
		//Then
		assertThat(exchangeList).hasSize(1);
        assertThat(exchangeList.get(0)).isEqualTo(exchange2);
	}
	
	@Test
	public void findMatchingByExchangeRWTest() {
		//Given
		
		//When
		List<Exchange> exchangeList = exchangeRepository.findMatchingByExchangeRW(3, 1);
		
		//Then
		assertThat(exchangeList).hasSizeGreaterThanOrEqualTo(1);
        assertThat(exchangeList).contains(exchange2);
	}

	@Test
	public void findMatchingByExchangeWRTest() {
		//Given
		
		//When
		List<Exchange> exchangeList = exchangeRepository.findMatchingByExchangeWR(3, 1);
		
		//Then
		assertThat(exchangeList).hasSizeGreaterThanOrEqualTo(1);
        assertThat(exchangeList).contains(exchange1);
	}
}
