package com.come.in.mypage;

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
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
    private ExchangeService exchangeService;
	
	@GetMapping("")
	public String mypage(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser != null) {
			List<Exchange> exchangeList = exchangeService.getExchangeByUserId(loginUser.get_id());
			
			model.addAttribute("exchangeList", exchangeList);
			return "mypage/main";			
		}else {
			return "redirect:/main";
		}
	}
	
}
