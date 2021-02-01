package org.aptiliux.restaurant.configuration.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private final Logger logger = LoggerFactory.getLogger(Receiver.class);

	@JmsListener(destination = "workerJMS", containerFactory = "jmsFactory")
	public void receiveMessage(Message message) {
		logger.info("Worker for: {}", message.getWorkUUID());
	}

}
