package com.come.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
}
