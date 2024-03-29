//package com.come.in.exchange;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.websocket.server.PathParam;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.come.in.user.User;
//import com.google.gson.Gson;
//
//@Controller
//@RequestMapping("/exchange")
//public class ExchangeController {
//
//	@Autowired
//    private ExchangeService exchangeService;
//	
//	@GetMapping("")
//	public String exchange(HttpServletRequest request, Model model) {
//		
//		HttpSession session = request.getSession();
//		User loginUser = (User)session.getAttribute("loginUser");
//		if(loginUser != null) {
//			List<Exchange> exchangeList = exchangeService.getExchangeByUserId(loginUser.get_id());
//			
//			model.addAttribute("exchangeList", exchangeList);
//			return "exchange/list";			
//		}else {
//			return "redirect:/main";
//		}
//	}
//	
//	@GetMapping("/add")
//	public String exchangeAddGet(HttpServletRequest request, Model model) {
//		
//		return "exchange/add";
//	}
//	
//	@PostMapping("/add")
//	public String exchangeAddPost(@PathParam(value = "_id") String _id, Exchange exchange, HttpServletRequest request, Model model) {
//		
//		if(_id != null && !"".equals(_id)) {
//			exchange.set_id(_id);			
//		}
//		exchangeService.insertExchange(exchange);
//		
//		return "redirect:/exchange";
//	}
//	
//	@GetMapping("/view")
//	public String exchangeViewGet(@PathParam(value = "_id") String _id, Model model) {
//		
//		Exchange exchange = exchangeService.getExchange(_id);
//		model.addAttribute("exchange", exchange);
//		return "exchange/view";
//	}
//	
//	@PostMapping("/del")
//	public String exchangeDelPost(@PathParam(value = "_id") String _id, Model model) {
//		
//		exchangeService.delExchange(_id);
//		return "redirect:/exchange";
//	}
//	
//	@GetMapping("/edit")
//	public String exchangeEditGet(@PathParam(value = "_id") String _id, Model model) {
//		
//		Exchange exchange = exchangeService.getExchange(_id);
//		model.addAttribute("exchange", exchange);
//		return "exchange/add";
//	}
//	
//	@GetMapping("/matching")
//	public String exchangeMatchingGet(@PathParam(value = "_id") String _id, HttpServletRequest request, Model model) {
//		
//		Exchange exchange = exchangeService.getExchange(_id);
//		
//		List<Exchange> matching = exchangeService.selectMatching(exchange);
//		model.addAttribute("matching", matching);
//		
//		model.addAttribute("exchange", exchange);
//		return "exchange/matching";
//	}
//	
//	@PostMapping("/requestMatching")
//	public void requestMatchingPost(HttpServletResponse response, @RequestParam String fromMatchingId, @RequestParam String toMatchingId) throws Exception {
//		
//		Exchange fromExchange = exchangeService.getExchange(fromMatchingId);
//		fromExchange.setMatchingId(toMatchingId);
//		fromExchange.setMatchingStatus("R");//R:요청 S:수락 W:수락대기
//		//exchangeService.insertExchange(fromExchange);
//		
//		Exchange toExchange = exchangeService.getExchange(toMatchingId);
//		toExchange.setMatchingId(toMatchingId);
//		toExchange.setMatchingStatus("W");//R:요청 S:수락 W:수락대기
//		//exchangeService.insertExchange(toExchange);
//		
//		Gson gson = new Gson();
//
//	    Map<String, Object> data = new HashMap<String, Object>();
//
//	    data.put("from", fromExchange.getUserId());
//	    data.put("to", toExchange.getUserId());
//	    data.put("status", "RW");
//
//	    response.getWriter().print(gson.toJson(data));
//	}
//	
//	@PostMapping("/acceptMatching")
//	public void acceptMatchingPost(HttpServletResponse response, @RequestParam String fromMatchingId, @RequestParam String toMatchingId) throws Exception {
//		
//		Exchange fromExchange = exchangeService.getExchange(fromMatchingId);
//		fromExchange.setMatchingStatus("S");//R:요청 S:수락 W:수락대기
//		//exchangeService.insertExchange(fromExchange);
//		
//		Exchange toExchange = exchangeService.getExchange(toMatchingId);
//		toExchange.setMatchingStatus("RS");//R:요청 S:수락 W:수락대기 RS:요청후매칭성공
//		//exchangeService.insertExchange(toExchange);
//		
//		Gson gson = new Gson();
//
//	    Map<String, Object> data = new HashMap<String, Object>();
//
//	    data.put("from", fromExchange.getUserId());
//	    data.put("to", toExchange.getUserId());
//	    data.put("status", "SRS");
//
//	    response.getWriter().print(gson.toJson(data));
//	}
//	
//	@GetMapping("/test")
//	@ResponseBody
//	public String testGet() {
//		Map<String, Object> data = new HashMap<String, Object>();
//
//	    data.put("from", "from");
//	    data.put("to", "to");
//	    data.put("status", "SRS");
//	    
//		Gson gson = new Gson();
//		return gson.toJson(data);
//	}
//	
//	@GetMapping("/test1")
//	@ResponseBody
//	public String testGet1() {
//		Map<String, Object> data = new HashMap<String, Object>();
//
//	    data.put("from", "to");
//	    data.put("to", "from");
//	    data.put("status", "SRSR");
//	    
//		Gson gson = new Gson();
//		return gson.toJson(data);
//	}
//	
//	@PostMapping("/test1")
//	@ResponseBody
//	public void testPost1(@RequestBody String body) {
//		System.out.println(body);
//	}
//}
