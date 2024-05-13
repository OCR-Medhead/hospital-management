package com.meahead.service;

import java.security.spec.KeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@Data
@Service
public class AuthenticationService {

	private static final String SECRET = "my-final-secret-jwt-is-really-really-simply-lol";
	private static final long EXPIRATION_TIME = 864_000_000; // 10 jours
	
	public static String generateToken(String userName) {
		System.out.println("username: " + userName);
		
		
		// claims
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("username", userName);
		claims.put("role", "user");
		
		
		byte[] secretBase64 = Base64.encodeBase64(SECRET.getBytes(), false);
		
		return Jwts.builder()
				.claim("username", userName)
				.claims().add(claims)
				.and()
				.subject("subject yo")
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, secretBase64)
				.compact();

				
	}
	
	public static String extractUserName(String token) {
		JwtParser test = Jwts.parser()
				.setSigningKey(SECRET)
				.build();
		
		return "lol";
//				.
////				.parseClaimsJws(token)
//				.getBody()
//				.getSubject();
	}
	
}
