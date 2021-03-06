package com.backend.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="com")

public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

	
	
	  public void configureMessageBroker(MessageBrokerRegistry registry) { 
	/*
	  /queue/ - destinationPrefix - send chat msg from server to client[Chat] //
	  /topic/ - destinationPrefix - send user name to all clients[String]*/
	  registry.enableSimpleBroker("/queue/","/topic/"); //for browser - to send
	//  messages from browser to server
	  registry.setApplicationDestinationPrefixes("/app");
	  
	  }
	  
	  public void registerStompEndpoints(StompEndpointRegistry registry) { // TODO
		  registry.addEndpoint("/chatmodule").withSockJS();
	  }
}
	 

