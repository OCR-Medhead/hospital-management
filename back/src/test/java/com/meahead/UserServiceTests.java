package com.meahead;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meahead.model.CustomUser;
import com.meahead.service.UserService;

@SpringBootTest
class UserServiceTests {

	@Autowired
	private UserService userService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void checkUserServiceFindById() {
		Optional<CustomUser> user = userService.findById((long)32);
		Long exceptedId = 32L;
		String exceptedMail = "dev@dev.fr";
		String exceptedFirstName = "dev";
		String exceptedLastName = "dev";
		
		assertTrue(user.isPresent());
		assertEquals(exceptedId, user.get().getId());
		assertEquals(exceptedMail, user.get().getEmail());
		assertEquals(exceptedFirstName, user.get().getFirstName());
		assertEquals(exceptedLastName, user.get().getLastName());
		
	}

	@Test
	public void checkUserServiceFindByEmail() {
		CustomUser user = userService.findUserByEmail("dev@dev.fr");
		Long exceptedId = 32L;
		String exceptedMail = "dev@dev.fr";
		String exceptedFirstName = "dev";
		String exceptedLastName = "dev";
		
		assertEquals(exceptedId, user.getId());
		assertEquals(exceptedMail, user.getEmail());
		assertEquals(exceptedFirstName, user.getFirstName());
		assertEquals(exceptedLastName, user.getLastName());
		
	}	
}
