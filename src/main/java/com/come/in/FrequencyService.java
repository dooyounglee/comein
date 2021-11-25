package com.come.in;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class FrequencyService {

	@Autowired
	private FrequencyRepository frequencyRepo;
	
	public List<Frequency> getAllFrequency() {
		return frequencyRepo.findAll();
	}
	
	public Optional<Frequency> getFrequency(String _id) {
		return frequencyRepo.findById(_id);
	}
	
	public Optional<Frequency> getFrequencyByUserId(String userId) {
		System.out.println(userId);
		return frequencyRepo.findByUserId(userId);
	}
}
