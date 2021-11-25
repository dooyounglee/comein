package com.come.in;

import java.util.List;
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

@Controller
public class HomeController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private FrequencyService frequencyService;
	
	@RequestMapping("/main")
	public String main(Model model) {
		
		return "main";
	}
	
	@GetMapping("/login")
	public String loginGet(@RequestParam Map<String, Object> paramMap, Model model) {
		
		for(String x : paramMap.keySet()) {
			System.out.println(x+": "+paramMap.get(x));
		}
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
		    
		    Optional<Frequency> frequency = frequencyService.getFrequencyByUserId(user.get().get_id());
		    if(frequency.isPresent()) {
			    session.setAttribute("loginFrequency", frequency.get());
		    }
		    
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
