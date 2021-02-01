package org.aptiliux.restaurant.configuration.jwt;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ExtendedUserDetails implements UserDetails {
	private static final long serialVersionUID = -5737928864576968842L;
	private final Long id;
	private final String username;
	private final String password;
	private final Set<? extends GrantedAuthority> grantedAuthorities;
	private final boolean isAccountNonExpired;
	private final boolean isAccountNonLocked;
	private final boolean isCredentialsNonExpired;
	private final boolean isEnabled;

	public ExtendedUserDetails(Long id, String username, String password,
			Set<? extends GrantedAuthority> grantedAuthorities, boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.grantedAuthorities = grantedAuthorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	public ExtendedUserDetails(Long id, String username, String password,
			Set<? extends GrantedAuthority> grantedAuthorities) {
		this(id, username, password, grantedAuthorities, true, true, true, true);
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
