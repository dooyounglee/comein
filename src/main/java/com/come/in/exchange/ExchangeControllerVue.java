package com.come.in.exchange;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Map<String, Object> exchange(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Exchange> exchanges = exchangeService.getExchangeByUserId(loginUser.get_id());
		for(Exchange ex : exchanges) {
			ex.setMatchingCount(exchangeService.selectMatching(ex).size());
		}
		resultMap.put("exchanges", exchanges);
		return resultMap;
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
	
	@PostMapping("/add")
	public Map<String, Object> exchangeAddPost(@PathParam(value = "_id") String _id, @RequestBody Exchange exchange, HttpServletRequest request) {
		
		if(_id != null && !"".equals(_id)) {
			exchange.set_id(_id);
		}
		exchangeService.insertExchange(exchange);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
	    User user = (User)session.getAttribute("loginUser");
	    List<Exchange> exchanges = exchangeService.getExchangeByUserId(user.get_id());
		
		for(Exchange ex : exchanges) {
			ex.setMatchingCount(exchangeService.selectMatching(ex).size());
		}
		resultMap.put("exchanges", exchanges);
		resultMap.put("message", "Create!!");
		return resultMap;
	}
	
	@PostMapping("/del")
	public Map<String, Object> exchangeDelPost(@RequestBody Exchange exchange) {
		System.out.println("_id" + exchange);
		exchangeService.delExchange(exchange.get_id());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message", "Delete!!");
		return resultMap;
	}
}
