package com.meahead.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.meahead.model.CustomUser;
import com.meahead.service.UserPrincipal;
import com.meahead.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

	// Clé secrète pour signer le token JWT
//    @Value("${app.jwtSecret}")
	private String jwtSecret = "my-final-secret-jwt-is-really-really-simply";
	private SecretKey key = Jwts.SIG.HS512.key().build();

	@Autowired
	private UserService userService;

	// Durée de validité du token JWT en millisecondes
//    @Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs = 864_000_000;

	// Générer un token JWT à partir d'une authentification
	public String generateToken(Authentication authentication) {
		System.out.println("Key de signature " + key);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		// claims
		Map<String, Object> claims = new HashMap<>();

		claims.put("username", userPrincipal.getUsername());
		claims.put("role", "user");

		return Jwts.builder().claim("username", userPrincipal.getUsername()).claims().add(claims).and()
				.subject(userPrincipal.getId().toString())
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).signWith(key).compact();
	}

	// Extraire l'ID utilisateur du token JWT
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

		return Long.parseLong(claims.getSubject());
	}

	// Valider le token JWT
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
			return true;
		} catch (SignatureException ex) {
			System.out.println("Signature JWT invalide");
		} catch (MalformedJwtException ex) {
			System.out.println("Token JWT malformé");
		} catch (ExpiredJwtException ex) {
			System.out.println("Token JWT expiré");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Token JWT non pris en charge");
		} catch (IllegalArgumentException ex) {
			System.out.println("Token JWT vide");
		}
		return false;
	}

	public String resolveToken(HttpServletRequest request) {
		// Récupérer le token JWT à partir de l'en-tête Authorization de la requête HTTP
		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			// Le token JWT est généralement préfixé par "Bearer "
			return bearerToken.substring(7);
		}

		return null;
	}

	public Authentication getAuthentication(String token) throws Exception {
		// Extraire l'identifiant de l'utilisateur à partir du token JWT
		Long userId = getUserIdFromJWT(token);

		// Charger les détails de l'utilisateur à partir de la base de données ou d'une autre source
		Optional<CustomUser> userDetails = userService.findById(userId);
		CustomUser user = userDetails.orElseThrow(() -> new Exception("Utilisateur introuvable"));

		// Créer une instance d'Authentication à partir des détails de l'utilisateur chargés
		return new UsernamePasswordAuthenticationToken(user, "", null);
	}

}