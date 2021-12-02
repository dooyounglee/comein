package com.come.in.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.messaging.handler.annotation.SendTo;

@Controller
public class ChatController {
    
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String chatGET(@RequestParam String str) throws Exception {
		System.out.println(str);
		return str;
    }
}