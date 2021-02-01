package org.aptiliux.restaurant.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			CredentialsRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(),
					CredentialsRequest.class);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword());

			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authResult) throws IOException, ServletException {

		ExtendedUserDetails extendedUserDetails = (ExtendedUserDetails) authResult.getPrincipal();

		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = LocalDateTime.now().plus(15, ChronoUnit.MINUTES);
		Function<LocalDateTime, Date> toDate = (localDate) -> Date
				.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		try {
			Algorithm algorithm = Algorithm.HMAC256("secretkey");
			String token = JWT.create().withSubject(authResult.getName()).withIssuedAt(toDate.apply(startDate))
					.withExpiresAt(toDate.apply(endDate)).withIssuer("restaurant")
					.withClaim("userid", extendedUserDetails.getId()).withClaim("role", authResult.getAuthorities()
							.stream().map(auth -> auth.getAuthority()).findFirst().orElse("WAITER"))
					.sign(algorithm);
			response.addHeader("Authorization", "Bearer " + token);
			filterChain.doFilter(request, response);
		} catch (JWTCreationException exception) {
			throw new RuntimeException(exception);
		}
	}
}
