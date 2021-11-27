package com.come.in.exchange;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ExchangeService {

	@Autowired
	private ExchangeRepository exchangeRepo;
	
	public Optional<Exchange> getExchange(String _id) {
		return exchangeRepo.findById(_id);
	}
	
	public List<Optional<Exchange>> getExchangeByUserId(String userId) {
		exchangeRepo.findByUserId(userId);
		return exchangeRepo.findByUserId(userId);
	}
	
	public void insertExchange(Exchange exchange) {
		exchangeRepo.save(exchange);
	}

	public void delExchange(String _id) {
		exchangeRepo.deleteById(_id);
	}
	
}
