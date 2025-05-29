package com.blog.api.config;

import java.net.Authenticator.RequestorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebsecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//@Autowired
	//private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(c->c.disable())
		
		.authorizeHttpRequests(
				(request) -> request
				.requestMatchers("/api/users/register","/api/users/demo","/api/users/login").permitAll()
				.anyRequest().authenticated())
//		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		;
		
		return http.build();
	}
	 
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		
		return provider;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder ()
	{
		return new BCryptPasswordEncoder(14);
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails userdetails = User
//				.withDefaultPasswordEncoder()
//				.username("aa")
//				.password("aa")
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(userdetails);
//	}
	

}

