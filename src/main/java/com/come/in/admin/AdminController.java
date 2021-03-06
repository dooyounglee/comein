package com.come.in.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.come.in.exchange.Exchange;
import com.come.in.exchange.ExchangeService;
import com.come.in.user.User;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private AdminService adminService;
	
	@GetMapping("/exchange")
	public String mypage(HttpServletRequest request, Model model) {
		
		List<Exchange> exchangeList = adminService.getAllExchange();
		model.addAttribute("exchangeList", exchangeList);
		
		return "admin/exchange/list";			
	}
}
