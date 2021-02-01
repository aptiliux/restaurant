package org.aptiliux.restaurant.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.aptiliux.restaurant.configuration.jwt.AuthenticationFilter;
import org.aptiliux.restaurant.configuration.jwt.ExtendedUserDetails;
import org.aptiliux.restaurant.configuration.jwt.TokenValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfiguration(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(new AuthenticationFilter(authenticationManager()))
				.addFilterAfter(new TokenValidationFilter(), AuthenticationFilter.class).authorizeRequests()
				.antMatchers("/", "index", "/css/*", "/js/*").permitAll().antMatchers("/api/**").hasRole("WAITER")
				.anyRequest().authenticated();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetailsService userDetailsService = new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Map<String, UserDetails> users = new HashMap<String, UserDetails>();
				users.put("user1", new ExtendedUserDetails(1L, "user1", passwordEncoder.encode("password"),
						Collections.singleton(new SimpleGrantedAuthority("ROLE_WAITER"))));
				users.put("user2", new ExtendedUserDetails(2L, "user2", passwordEncoder.encode("password"),
						Collections.singleton(new SimpleGrantedAuthority("ROLE_WAITER"))));

				UserDetails user = users.get(username.toLowerCase());
				if (user == null) {
					throw new UsernameNotFoundException(username);
				}
				return user;
			}
		};

		return userDetailsService;

	}
}
