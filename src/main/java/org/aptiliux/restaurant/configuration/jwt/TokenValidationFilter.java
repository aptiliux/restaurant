package org.aptiliux.restaurant.configuration.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class TokenValidationFilter extends OncePerRequestFilter {

	public TokenValidationFilter() {

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String bearerPrefix = "Bearer ";

		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || authorizationHeader.isEmpty()
				|| !authorizationHeader.startsWith(bearerPrefix)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authorizationHeader.replace(bearerPrefix, "");

		try {
			Algorithm algorithm = Algorithm.HMAC256("secretkey");
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("restaurant").build();
			DecodedJWT jwt = verifier.verify(token);
			String username = jwt.getSubject();
			String role = jwt.getClaim("role").asString();
			Long userId = jwt.getClaim("userid").asLong();

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					new UserPrincipal(userId, username), null, Collections.singleton(new SimpleGrantedAuthority(role)));
			authentication.setDetails(null);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		} catch (JWTVerificationException exception) {
			throw new RuntimeException(exception);
		}
	}
}
