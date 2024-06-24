package com.tindering.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class jwtValdator extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(jwtValdator.class);

	//@Autowired
   //private jwtConstant jwtConstant;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt=request.getHeader(jwtConstant.JWT_HEADER);
		
		if(jwt!=null) {
			try {
				logger.debug("Attempting to parse JWT: {}", jwt);
				String email=JwtProvider.getEmailFromJwtToken(jwt);
				logger.debug("JWT parsed successfully, email: {}", email);

				List<GrantedAuthority> authorities=new ArrayList<>();
				Authentication authentication=new UsernamePasswordAuthenticationToken(email,null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch (ExpiredJwtException e) {
				logger.error("Token has expired: {}", e.getMessage());
				throw new BadCredentialsException("Token has expired", e);
			} catch (MalformedJwtException e) {
				logger.error("Invalid token format: {}", e.getMessage());
				throw new BadCredentialsException("Invalid token format", e);
			} catch (SignatureException e) {
				logger.error("Invalid token signature: {}", e.getMessage());
				throw new BadCredentialsException("Invalid token signature", e);
			}  catch (Exception e) {
				throw new BadCredentialsException("Invalid token !!");
			}
		}
		else {
			logger.warn("No JWT token found in request header");
		}
		
		filterChain.doFilter(request, response);
		
	}

	
}
