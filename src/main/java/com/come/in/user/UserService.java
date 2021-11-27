package com.come.in.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	public Optional<User> getUser(String _id) {
		return userRepo.findById(_id);
	}
}
