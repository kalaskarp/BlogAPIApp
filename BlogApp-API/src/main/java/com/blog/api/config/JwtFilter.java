package com.blog.api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.api.services.MyUserDetails;
import com.blog.api.services.impl.JWTServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JWTServiceImpl jwtserServiceImpl;
	

	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String Username = null;
		System.out.println("into dofilter"+authHeader);
		
		if(authHeader == null|| !authHeader.startsWith("Bearer") ) {
			filterChain.doFilter(request, response);
			
			return;
			
		}
		token = authHeader.substring(7);
		System.out.println("auth "+authHeader);
		Username = jwtserServiceImpl.extractUserName(token);
		System.out.println(authHeader);
		
		if (Username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userdetails = userDetailsService.loadUserByUsername(Username);
			
			if (jwtserServiceImpl.validateToken(token,userdetails)) {
				
				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
				
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
