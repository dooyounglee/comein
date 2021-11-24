package com.come.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
    private Service service;
	
	@RequestMapping("/main")
	public String main(Model model) {
		
		List<User> userList = service.getAllUser();
		
		model.addAttribute("userList", userList);
		return "main";
	}
}
