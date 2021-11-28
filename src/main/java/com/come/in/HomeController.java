package com.come.in;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.come.in.user.User;
import com.come.in.user.UserService;

@Controller
public class HomeController {

	@Autowired
    private UserService userService;
	
	@RequestMapping("/main")
	public String main(Model model) {
		
		return "main";
	}
	
	@GetMapping("/login")
	public String loginGet(@RequestParam Map<String, Object> paramMap, Model model) {
		
		model.addAttribute("paramMap", paramMap);
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, Model model) {
		
		Optional<User> user = userService.getUser((String) paramMap.get("id"));
		model.addAttribute("paramMap", paramMap);
		if(user.isPresent()) {
			// 로그인 성공 처리
		    HttpSession session = request.getSession();
		    session.setAttribute("loginUser", user.get());
		    
			return "redirect:/main";
		}else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logOut")
	public String logoutGet(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }

	    return "redirect:/main";
	}
	
}
