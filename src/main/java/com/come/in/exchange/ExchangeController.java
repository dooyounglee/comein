package com.come.in.exchange;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.come.in.user.User;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

	@Autowired
    private ExchangeService exchangeService;
	
	@GetMapping("")
	public String exchange(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser != null) {
			List<Exchange> exchangeList = exchangeService.getExchangeByUserId(loginUser.get_id());
			
			model.addAttribute("exchangeList", exchangeList);
			return "exchange/list";			
		}else {
			return "redirect:/main";
		}
	}
	
	@GetMapping("/add")
	public String exchangeAddGet(HttpServletRequest request, Model model) {
		
		return "exchange/add";
	}
	
	@PostMapping("/add")
	public String exchangeAddPost(@PathParam(value = "_id") String _id, Exchange exchange, HttpServletRequest request, Model model) {
		
		if(_id != null && !"".equals(_id)) {
			exchange.set_id(_id);			
		}
		exchangeService.insertExchange(exchange);
		
		return "redirect:/exchange";
	}
	
	@GetMapping("/view")
	public String exchangeViewGet(@PathParam(value = "_id") String _id, Model model) {
		
		Exchange exchange = exchangeService.getExchange(_id);
		model.addAttribute("exchange", exchange);
		return "exchange/view";
	}
	
	@PostMapping("/del")
	public String exchangeDelPost(@PathParam(value = "_id") String _id, Model model) {
		
		exchangeService.delExchange(_id);
		return "redirect:/exchange";
	}
	
	@GetMapping("/edit")
	public String exchangeEditGet(@PathParam(value = "_id") String _id, Model model) {
		
		Exchange exchange = exchangeService.getExchange(_id);
		model.addAttribute("exchange", exchange);
		return "exchange/add";
	}
	
	@GetMapping("/matching")
	public String exchangeMatchingGet(@PathParam(value = "_id") String _id, HttpServletRequest request, Model model) {
		
		Exchange exchange = exchangeService.getExchange(_id);
		
		List<Exchange> matching = exchangeService.selectMatching(exchange);
		model.addAttribute("matching", matching);
		
		model.addAttribute("exchange", exchange);
		return "exchange/matching";
	}
}
