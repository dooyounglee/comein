package com.come.in;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;

@Configuration
public class WebSocketConfig0 extends WebSocketMessageBrokerConfigurationSupport{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS();
	}

	//@Override
	//public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
	//}

	//@Override
	//protected SimpUserRegistry createLocalUserRegistry(Integer order) {
	//	// TODO Auto-generated method stub
	//	return null;
	//}

}
