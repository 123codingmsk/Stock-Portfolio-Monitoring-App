package com.example.stockPortfolio.UserManagementTest;

import com.example.stockPortfolio.UserManagement.LoginRequestDTO;
import com.example.stockPortfolio.UserManagement.UserController;
import com.example.stockPortfolio.UserManagement.UserModel;
import com.example.stockPortfolio.UserManagement.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// This annotation sets up the testing environment ONLY for the controller part
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc  mockMvc; // allows us to "fake" HTTP requests without starting server

    @MockBean
    private UserService  userService; // we fake the service so no real DB is used

    @Autowired
    private ObjectMapper objectMapper; // helps us to convert Java objects to JSON

    //Our main testing starts here
    @Test
    void testRegisterUser() throws Exception {

        // created a sample dummy user
        UserModel user = new UserModel();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("pass123");

        // we mock the behavior of userService
        Mockito.when(userService.register(any(UserModel.class))).thenReturn(user);

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))) // converting user object to JSON
                .andExpect(status().isOk()) //displays status as 200 Ok
                .andExpect(jsonPath("$.name").value("John Doe")) // this is what we expect to get a particular expression
                .andExpect(jsonPath("$.email").value("john@example.com"));//we expect this value to get back
    }

    @Test
    void testLogin() throws Exception {
        LoginRequestDTO login = new LoginRequestDTO();
        login.setEmail("john@example.com");
        login.setPassword("pass123");

        Mockito.when(userService.login("john@example.com", "pass123"))
                .thenReturn("Login Successful!");

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login Successful!"));
    }

    @Test
    void testUpdateProfile() throws Exception {
        String email = "john@example.com";

        UserModel updateData = new UserModel();
        updateData.setName("Johnny Updated");
        updateData.setEmail("john.updated@example.com");
        updateData.setPassword("updated123");

        Mockito.when(userService.editProfile(Mockito.eq(email), any(UserModel.class)))
                .thenReturn(updateData);

        mockMvc.perform(put("/user/updateProfile/" + email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Johnny Updated"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"));
    }


    void test_toCheckUpdate() throws Exception{
        String email = "john@example.com";

        UserModel updateData = new UserModel();

        updateData.setName("d");
        updateData.setEmail("john.updated12@gmail.com");
        updateData.setPassword("12345");

        UserModel user ;
        Mockito.when(userService.editProfile(Mockito.eq(email),any( UserModel.class))).thenReturn(updateData);


    }
}