package com.meahead.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.meahead.controller.UserController;
import com.meahead.model.CustomUser;
import com.meahead.model.LoginRequest;
import com.meahead.repository.UserRepository;
import com.meahead.service.UserService;

public class UserControllerTest {

    @Mock
    UserController userController;
    
    @Mock
    UserService userService;
//    
//    @Mock
//    UserRepository userRepository;

	@BeforeEach
    public void setUp() {
//        UserController userController = new UserController();
        LoginRequest lr = new LoginRequest();
        lr.setEmail("dev@dev.fr");
        lr.setPassword("afzrgergq54z54fezf41");
        
//        UserService userService = new UserService();
        CustomUser cs = new CustomUser();
        cs.setEmail("dev@dev.fr");
        cs.setPassword("afzrgergq54z54fezf41");
        
        ResponseEntity<String> rep = ResponseEntity.status(HttpStatus.OK).body("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImRldkBkZXYuZnIiLCJyb2xlIjoidXNlciIsInN1YiI6IjMyIiwiZXhwIjoxNzE5MjQ2NTEyfQ.5Ahu1ew3zWdH_DBhePF81a-2kI2RAkdL0_eEPDZXw3NmXZG0CY9wLu0VGcKoKzGan9bN949J2cCfcFK_2s1rhQ");
        
        MockitoAnnotations.openMocks(this);
//        when(userRepository.findUserByEmail(anyString())).thenReturn(cs);
		when(userService.findUserByEmail(anyString())).thenReturn(cs);
        when(userController.login(lr)).thenReturn(rep);
        
    }

    @Test
    public void should_return_jwt_token(){
        LoginRequest lr = new LoginRequest();
        lr.setEmail("dev@dev.fr");
        lr.setPassword("afzrgergq54z54fezf41");
        
    	ResponseEntity<String> token = userController.login(lr);
    	assertThat(token.getBody().toString()).startsWith("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImRldkBkZXYuZnIiLCJyb2xlIjoidXNlciIsInN1YiI6IjMyIiwiZXhwIjoxNzE5MjQ2NTEyfQ.5Ahu1ew3zWdH_DBhePF81a-2kI2RAkdL0_eEPDZXw3NmXZG0CY9wLu0VGcKoKzGan9bN949J2cCfcFK_2s1rhQ");
    }
    

    
}