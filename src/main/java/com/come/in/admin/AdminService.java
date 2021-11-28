package com.come.in.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.come.in.exchange.Exchange;
import com.come.in.exchange.ExchangeRepository;

@Service
public class AdminService {

	@Autowired
	private ExchangeRepository exchangeRepo;
	
	public Exchange getExchange(String _id) {
		return exchangeRepo.findById(_id).get();
	}

	public List<Exchange> getAllExchange() {
		return exchangeRepo.findAll();
	}
}
