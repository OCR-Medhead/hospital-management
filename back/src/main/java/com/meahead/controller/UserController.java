package com.meahead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.meahead.config.JwtTokenProvider;
import com.meahead.model.CustomUser;
import com.meahead.model.LoginRequest;
import com.meahead.service.UserPrincipal;
import com.meahead.service.UserService;

@CrossOrigin(origins="*")
@RestController
public class UserController {

	@Autowired
	private UserService us;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
		
	@PostMapping("/user/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
	
		CustomUser customUser = us.findUserByEmail(loginRequest.getEmail());

		if(customUser == null || customUser.getEmail() == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le login / mot de passe ne correspondent pas");
		}
		
		if(!(customUser.getPassword().equals(loginRequest.getPassword()))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le login / mot de passe ne correspondent pas" + customUser.getPassword() + loginRequest.getPassword());
		}
		UserPrincipal user = new UserPrincipal();
		user.setUsername(customUser.getEmail());
		user.setId(customUser.getId());
		return ResponseEntity.status(HttpStatus.OK).body(jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(user, "", null)));
	}
	
	
}