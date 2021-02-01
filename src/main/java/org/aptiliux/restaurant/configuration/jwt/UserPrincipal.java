package org.aptiliux.restaurant.configuration.jwt;

public class UserPrincipal {
	
	private Long userId;
	private String username;

	public UserPrincipal(Long userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}
	
	public Long getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}

}
