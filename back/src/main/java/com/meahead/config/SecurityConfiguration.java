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
		
//	protected void configure(HttpSecurity http) throws Exception{
//		System.out.println("Service configuré !");
//		http.csrf().disable()
//		.authorizeRequests()
//		.anyRequest().permitAll();
////		.requestMatchers("/user/login", "/token").permitAll() // paths are exclude from authentication requirement
////		.anyRequest().authenticated();
//	}
		
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.cors(Customizer.withDefaults());
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
				
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		http
		.authorizeHttpRequests((authorize) -> authorize
//				.requestMatchers("/user/secret").authenticated() // ca marche
				.requestMatchers("/user/login").anonymous() // ca marche
//				.requestMatchers(HttpMethod.POST," /user/test").anonymous()
				.anyRequest().authenticated()
				).csrf((csrf) -> csrf.disable());
		
		// Application du JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	
		
	return http.build();
	}
	
}
