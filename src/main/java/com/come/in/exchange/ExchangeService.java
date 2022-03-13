package com.come.in.exchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

	@Autowired
	private ExchangeRepository exchangeRepo;
	
	public Exchange getExchange(String _id) {
		return exchangeRepo.findById(_id).get();
	}
	
	public List<Exchange> getExchangeByUserId(String userId) {
		return exchangeRepo.findByUserId(userId);
	}
	
	public void insertExchange(Exchange exchange) {
		exchangeRepo.save(exchange);
	}

	public void delExchange(String _id) {
		exchangeRepo.deleteById(_id);
	}

	public List<Exchange> selectMatching(Exchange newExchange) {
		if(newExchange.getMyR() != 0 && newExchange.getExW() != 0) {
			return exchangeRepo.findMatchingByExchangeRW(newExchange.getMyR(), newExchange.getExW());
		}else {
			return exchangeRepo.findMatchingByExchangeWR(newExchange.getMyW(), newExchange.getExR());
		}
	}

	public Map<String, Object> requestMatching(String fromMatchingId, String toMatchingId) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Exchange fromExchange = getExchange(fromMatchingId);
		fromExchange.setMatchingId(toMatchingId);
		fromExchange.setMatchingStatus("R");//R:요청 S:수락 W:수락대기
		insertExchange(fromExchange);
		
		Exchange toExchange = getExchange(toMatchingId);
		toExchange.setMatchingId(fromMatchingId);
		toExchange.setMatchingStatus("W");//R:요청 S:수락 W:수락대기
		insertExchange(toExchange);
		
		resultMap.put("fromExchangeUserId", fromExchange.getUserId());
		resultMap.put("toExchangeUserId", toExchange.getUserId());
		resultMap.put("status", "RW");
		
		return resultMap;
	}

	public void updateMatchingStatus(String fromMatchingId, String status) {
		Exchange exchange = exchangeRepo.findById(fromMatchingId).get();
		exchange.setMatchingStatus(status);
		exchangeRepo.save(exchange);
	}

	public Map<String, Object> acceptMatching(String fromMatchingId, String toMatchingId) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Exchange fromExchange = getExchange(fromMatchingId);
		fromExchange.setMatchingId(toMatchingId);
		fromExchange.setMatchingStatus("S");//R:요청 S:수락 W:수락대기
		exchangeRepo.save(fromExchange);
		
		Exchange toExchange = getExchange(toMatchingId);
		toExchange.setMatchingId(fromMatchingId);
		toExchange.setMatchingStatus("RS");//R:요청 S:수락 W:수락대기
		exchangeRepo.save(toExchange);
		
		resultMap.put("fromExchangeUserId", fromExchange.getUserId());
		resultMap.put("toExchangeUserId", toExchange.getUserId());
		resultMap.put("status", "SRS");
		
		return resultMap;
	}
}
