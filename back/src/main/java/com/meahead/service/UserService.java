package com.meahead.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meahead.model.CustomUser;
import com.meahead.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Optional<CustomUser> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public CustomUser findUserByEmail(String email) {
			return userRepository.findUserByEmail(email);
	}	
	
//	public CustomUser login(String email) {
//		
//		CustomUser user = this.findUserByEmail(email);
//	return user;
//	}
	
	public String checkToken(String token) {
		return token;
	}
	
}

