package org.aptiliux.restaurant.configuration.jms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	private String workUUID;

	public Message(@JsonProperty("workUUID") String workUUID) {
		this.workUUID = workUUID;
	}
	
	public String getWorkUUID() {
		return workUUID;
	}
	
}
