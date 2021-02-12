package com.pro.mxpro.commons;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	private static Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry){
		logger.info("{}","技记立加");
		registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		logger.info("{}","configureMessageBroker 宏肺目 立加");
		config.enableSimpleBroker("/topic");
		
		config.setApplicationDestinationPrefixes("/app");
	}

}
