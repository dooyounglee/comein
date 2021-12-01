package com.come.in.exchange;

import java.util.List;

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
		exchangeRepo.findByUserId(userId);
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

	public void requestMatching(String fromMatchingId, String toMatchingId) {
		Exchange exchange = exchangeRepo.findById(fromMatchingId).get();
		exchange.setMatchingId(toMatchingId);
		exchangeRepo.save(exchange);
	}

	public void updateMatchingStatus(String fromMatchingId, String status) {
		Exchange exchange = exchangeRepo.findById(fromMatchingId).get();
		exchange.setMatchingStatus(status);
		exchangeRepo.save(exchange);
	}
	
}
