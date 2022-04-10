package com.come.in.exchange;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.come.in.user.User;

@RestController
@RequestMapping("/exchange")
public class ExchangeControllerVue {

	@Autowired
    private ExchangeService exchangeService;
	
	@GetMapping("/list")
	public List<Exchange> exchange(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		return exchangeService.getExchangeByUserId(loginUser.get_id());
	}
}
