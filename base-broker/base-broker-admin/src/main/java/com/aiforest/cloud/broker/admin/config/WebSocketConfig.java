package com.aiforest.cloud.broker.admin.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aiforest.cloud.broker.common.constant.WxBRConstants;
import com.aiforest.cloud.broker.common.entity.ThirdSession;
import com.aiforest.cloud.weixin.common.constant.WxMaConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.session.Session;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {

//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	private final RedisTemplate redisTemplate;

//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		log.info("WebSocket服务器注册");
//		registry.addEndpoint("/ws")
//				.setAllowedOrigins("*")
//				.withSockJS();
//		registry.addEndpoint("/ss").setAllowedOrigins("*");
//
//	}

	@Override
	protected void configureStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
		registry.addEndpoint("/ss").setAllowedOrigins("*");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		log.info("WebSocket服务器启动");
		registry.enableStompBrokerRelay("/queue/", "/topic/", "/exchange/");
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				// 判断是否首次连接请求
				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//					String tokens = accessor.getFirstNativeHeader("Authorization");
					Object obj = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
					log.info("webSocket is {}", obj);
//					if (x instanceof Collection<?>){
//					}
					if (obj instanceof Map<?,?>) {
						Object tokens = ((Map) obj).get("token");

						List<String> list = castList(tokens, String.class);

						if (StrUtil.isNotBlank(list.get(0))) {
							//获取缓存中的ThirdSession
							String key1 = WxBRConstants.THIRD_SESSION_BEGIN + ":" + list.get(0);
							Object thirdSessionObj1 = redisTemplate.opsForValue().get(key1);
							String key2 = WxMaConstants.THIRD_SESSION_BEGIN + ":" + list.get(0);
							Object thirdSessionObj2 = redisTemplate.opsForValue().get(key2);

//							Object thirdSessionObj = thirdSessionObj1 != null ?  thirdSessionObj1 : thirdSessionObj2;

							if (thirdSessionObj1 == null && thirdSessionObj2 == null) {//session过期
								return null;
							} else {
								if (thirdSessionObj1 != null) {
									String thirdSessionStr1 = String.valueOf(thirdSessionObj1);
									ThirdSession thirdSession1 = JSONUtil.toBean(thirdSessionStr1, ThirdSession.class);
									accessor.setUser(() -> thirdSession1.getUserId());
									return message;
								}
								String thirdSessionStr2 = String.valueOf(thirdSessionObj2);
								com.aiforest.cloud.weixin.common.entity.ThirdSession thirdSession2 = JSONUtil.toBean(thirdSessionStr2, com.aiforest.cloud.weixin.common.entity.ThirdSession.class);
								accessor.setUser(thirdSession2::getEstateUserId);
								return message;
							}
						}
					}

					return null;

//					if (StrUtil.isBlank(tokens)) {
//						return null;
//					}
					// 验证令牌信息
//					OAuth2Authentication auth2Authentication = tokenService.loadAuthentication(tokens.split(" ")[1]);
//					if (ObjectUtil.isNotNull(auth2Authentication)) {
//						SecurityContextHolder.getContext().setAuthentication(auth2Authentication);
//						accessor.setUser(() -> auth2Authentication.getName());
//						return message;
//					} else {
//						return null;
//					}
				}
				//不是首次连接，已经成功登陆
				return message;
			}
		});

	}

	public static <T> List<T> castList(Object obj, Class<T> clazz)
	{
		List<T> result = new ArrayList<>();
		if(obj instanceof List<?>)
		{
			for (Object o : (List<?>) obj)
			{
				result.add(clazz.cast(o));
			}
			return result;
		}
		return null;
	}

}
