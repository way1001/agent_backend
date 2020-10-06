package com.aiforest.cloud.broker.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	@Override
	public void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		messages.simpMessageDestMatchers("**")
				.denyAll()
				.anyMessage()
				.permitAll();
	}

    @Override
	public boolean sameOriginDisabled() {
		//disable CSRF for websockets for now...
		return true;
	}

}
