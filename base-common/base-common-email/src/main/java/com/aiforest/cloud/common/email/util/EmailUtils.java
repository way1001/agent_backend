package com.aiforest.cloud.common.email.util;

import com.aiforest.cloud.common.email.config.EmailConfigProperties;
import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AllArgsConstructor
public class EmailUtils {
	private final EmailConfigProperties emailConfigProperties;

	public void sendEmail(String to, String title, String content) throws SendMailException {
		OhMyEmail.subject(title)
				.from(emailConfigProperties.getSiteName())
				.to(to)
				.html(content)
				.send();
		log.info("email: {} send success", to);
	}
}
