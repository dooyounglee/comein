package com.come.in.exchange;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.come.in.user.User;
import com.come.in.user.UserService;

@RestController
@RequestMapping("/exchange")
public class ExchangeControllerVue {

	@Autowired
    private UserService userService;
	
	@Autowired
    private ExchangeService exchangeService;
	
	@GetMapping("/list")
	public List<Exchange> exchange(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		return exchangeService.getExchangeByUserId(loginUser.get_id());
	}
	
	@GetMapping("/matching")
	public List<Exchange> exchangeMatchingGet(@PathParam(value = "_id") String _id) {
		Exchange exchange = exchangeService.getExchange(_id);
		
		List<Exchange> exchanges = exchangeService.selectMatching(exchange);
		
		for(Exchange ex : exchanges) {
			ex.setUserName(userService.getUser(ex.getUserId()).get().getName());
		}
		return exchanges;
	}
}
