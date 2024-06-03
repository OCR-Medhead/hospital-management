package com.meahead.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

// @Configuration permet de charger la configuration par Spring
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	// On définit un provider jwt
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
				
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors().configurationSource(request -> {
			var cors = new CorsConfiguration();
		      cors.setAllowedOrigins(List.of("*"));
		      cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
		      cors.setAllowedHeaders(List.of("*"));
		      return cors;
		});
		
		// Aucune session ne sera créé par Spring Security
		http
		.sessionManagement((sm) -> sm
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);
		
		http
		.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/user/login").anonymous()
				.anyRequest().authenticated()
				).csrf((csrf) -> csrf.disable());
		
		// Application du JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	
	return http.build();
	}
	
}
