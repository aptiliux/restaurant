package org.aptiliux.restaurant.configuration.jms;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class Sender {
	
	private final Logger logger = LoggerFactory.getLogger(Sender.class);
	private final JmsTemplate jmsTemplate;
	
	public Sender(JmsTemplate jmsTemplate) {
		super();
		this.jmsTemplate = jmsTemplate;
	}

	public String sendMessage() {
		String workUUID = Base64Utils.encodeToUrlSafeString((UUID.randomUUID()).toString().getBytes());
		Message message =  new Message(workUUID);
	    logger.info("Sending message: {}", message.getWorkUUID());
	    jmsTemplate.convertAndSend("workerJMS", message);
	    return workUUID;
	}


}
