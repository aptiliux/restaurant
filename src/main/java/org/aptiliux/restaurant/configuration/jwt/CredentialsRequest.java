package org.aptiliux.restaurant.configuration.jwt;

public class CredentialsRequest {

	private String username;
	private String password;

	public CredentialsRequest() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
