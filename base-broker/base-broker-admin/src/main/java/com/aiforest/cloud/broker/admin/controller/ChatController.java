package com.aiforest.cloud.broker.admin.controller;

import com.aiforest.cloud.broker.admin.event.LoginEvent;
import com.aiforest.cloud.broker.admin.event.ParticipantRepository;
import com.aiforest.cloud.broker.common.dto.ChatMessage;
import com.aiforest.cloud.broker.common.dto.SessionProfanity;
import com.aiforest.cloud.broker.common.utils.ProfanityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Collection;

@Controller
public class ChatController {

	@Autowired private ProfanityChecker profanityFilter;

	@Autowired private SessionProfanity profanity;

	@Autowired private ParticipantRepository participantRepository;

	@Autowired private SimpMessagingTemplate simpMessagingTemplate;

	@SubscribeMapping("/chat.participants")
	public Collection<LoginEvent> retrieveParticipants() {
		return participantRepository.getActiveSessions().values();
	}

	@MessageMapping("/chat.message")
	public ChatMessage filterMessage(@Payload ChatMessage message, Principal principal){
		checkProfanityAndSanitize(message);

		message.setId(principal.getName());
		return message;
	}

	@MessageMapping("/chat.private/{userId}")
	public void filterPrivateMessage(@Payload ChatMessage message, @DestinationVariable("userId") String userId, Principal principal){
		checkProfanityAndSanitize(message);

		message.setId(principal.getName());
		simpMessagingTemplate.convertAndSend("/user/" + userId + "/exchange/amq.direct/chat.message", message);
	}

	private void checkProfanityAndSanitize(ChatMessage message) {
		long profanityLevel = profanityFilter.getMessageProfanity(message.getMessage());
		profanity.increment(profanityLevel);
		message.setMessage(profanityFilter.filter(message.getMessage()));
	}

	@MessageExceptionHandler
	@SendToUser(value = "/exchange/amq.direct/errors", broadcast = false)
	public String handleProfanity(RuntimeException e) {
		return e.getMessage();
	}
}
