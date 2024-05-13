package com.meahead.service;

import java.security.Principal;
import java.util.Collection;

import lombok.Data;

@Data
public class UserPrincipal implements Principal {
	private Long id;
	private String username;
	private String password;
	private Collection<String> roles;
	
	@Override
	public String getName() {
		return this.getUsername();
		// TODO Auto-generated method stub
//		return null;
	}
}
