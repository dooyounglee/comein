package com.come.in.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.come.in.exchange.ExchangeService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/users")
public class UserControllerVue {

	@Autowired
    private UserService userService;
	
	@Autowired
    private ExchangeService exchangeService;
	
	@GetMapping("/list")
	public List<User> getUserList() {
		List<User> exchangeList = userService.getAllUser();
		return exchangeList;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> loginPost(@RequestBody String body, HttpServletRequest request) {
		Gson gson = new Gson();
		Map<String, Object> paramMap = gson.fromJson(body, Map.class);
		Optional<User> user = userService.getUser((String) paramMap.get("_id"));
		if(user.isPresent()) {
			// 로그인 성공 처리
		    HttpSession session = request.getSession();
		    session.setAttribute("loginUser", user.get());
		    
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("user", user.get());
		    map.put("frequencies", exchangeService.getExchangeByUserId(user.get().get_id()));
		    return map;
		}else {
			return null;
		}
	}
	
	@GetMapping("/checkSession")
	@ResponseBody
	public User checkSessionGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		System.out.println(user);
		return user;
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public void logoutGet(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	}
}
