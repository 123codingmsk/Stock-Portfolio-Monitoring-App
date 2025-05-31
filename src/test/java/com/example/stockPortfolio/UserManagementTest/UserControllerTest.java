package com.example.stockPortfolio.UserManagementTest;

import com.example.stockPortfolio.UserManagement.LoginRequestDTO;
import com.example.stockPortfolio.UserManagement.UserController;
import com.example.stockPortfolio.UserManagement.UserModel;
import com.example.stockPortfolio.UserManagement.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    UserModel user;
    LoginRequestDTO loginRequest;

    @BeforeEach
    void setUp() {
        user = new UserModel();

        user.setName("TCS");
        user.setEmail("Dev123@gmail.com");
        user.setPassword("1234");

        loginRequest = new LoginRequestDTO();
        loginRequest.setEmail("Deva123@gmail.com");
        loginRequest.setPassword("01125532553");

    }

    @Test
    void test_register() {
        Mockito.when(userService.register(user)).thenReturn(user);

        UserModel u1= userController.register(user);

        assertEquals("TCD",u1.getName());
        assertEquals("1234",u1.getPassword());
        Mockito.verify(userService).register(user);
    }

    @Test
    void test_login() {

        Mockito.when(userService.login("Deva123@gmail.com","01125532553")).thenReturn("Login Successful!");

        //as we are getting string as outcome
        String result = userController.login(loginRequest); //will return "Success" if executed

        assertEquals("Login Successful!",result);
        Mockito.verify(userService).login("Deva123@gmail.com","01125532553");
    }

    @Test
    void update() {


        Mockito.when(userService.editProfile("Deva123@gmail.com",user)).thenReturn(user);

        UserModel updatedUser = userController.update("Deva123@gmail.com",user);

        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals("TCS",updatedUser.getName());
        Assertions.assertEquals("1234", updatedUser.getPassword());

        Mockito.verify(userService).editProfile("Deva123@gmail.com",user);

    }
}