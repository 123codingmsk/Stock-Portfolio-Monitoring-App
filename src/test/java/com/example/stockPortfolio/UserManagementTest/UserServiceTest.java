package com.example.stockPortfolio.UserManagementTest;

import com.example.stockPortfolio.UserManagement.UserModel;
//import com.example.stockPortfolio.UserManagement.UserNotFoundException;
import com.example.stockPortfolio.UserManagement.UserRepo;
import com.example.stockPortfolio.UserManagement.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserService userService;

    private UserModel testUsers;

    @BeforeEach
    public void setUp(){
        testUsers = new UserModel();
        testUsers.setEmail("test1@gmail.com");
        testUsers.setName("t1");
        testUsers.setPassword("psw");
        testUsers.setUserId(1L);
    }

    @Test
    public void registrationWhenUserDoesNotExistTest(){
        when(userRepo.existsByEmail(testUsers.getEmail())).thenReturn(false);
        when(userRepo.save(testUsers)).thenReturn(testUsers);

        UserModel result = userService.register(testUsers);

        assertNotNull(result);
        assertEquals(testUsers.getEmail(),result.getEmail());
        verify(userRepo).save(testUsers);
    }

//    @Test
//    public void registrationWhenUserExistsTest(){
//        when(userRepo.existsByEmail(testUsers.getEmail())).thenReturn(true);
//
//        Exception exception = assertThrows(UserNotFoundException.class,() -> userService.register(testUsers));
//        assertEquals("User already exists in our database",exception.getMessage());
//        verify(userRepo,never()).save(any());
//    }

    @Test
    public void TestForSuccessfullLogin(){
        when(userRepo.findByEmail(testUsers.getEmail())).thenReturn(Optional.of(testUsers));

        String result = userService.login(testUsers.getEmail(),testUsers.getPassword());

        assertEquals(result,"Login Successful!");
    }

//    @Test
//    public void TestForIncorrectPassword(){
//        when(userRepo.findByEmail(testUsers.getEmail())).thenReturn((Optional.of(testUsers)));
//
//        Exception exception = assertThrows(UserNotFoundException.class,()-> userService.login("test1@gmail.com","pass"));
//
//        assertEquals(exception.getMessage(),"Incorrect password!");
//    }

//    @Test
//    public void TestForInvalidEmailId(){
//        when(userRepo.findByEmail("test123@gmail.com")).thenReturn((Optional.empty()));
//
//        Exception exception = assertThrows(UserNotFoundException.class,() -> {userService.login("test123@gmail.com","123");});
//
//        assertEquals("Email not present in our database. Please register",exception.getMessage());
//    }

    @Test
    void TestEditProfileSuccess() {
        UserModel updatedUser = new UserModel();
        updatedUser.setEmail("new@example.com");
        updatedUser.setName("Jane Doe");
        updatedUser.setPassword("newPassword");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(testUsers));
        when(userRepo.save(any(UserModel.class))).thenReturn(updatedUser);

        UserModel result = userService.editProfile("test@example.com", updatedUser);

        assertEquals("new@example.com", result.getEmail());
        assertEquals("Jane Doe", result.getName());
        assertEquals("newPassword", result.getPassword());
    }

//    @Test
//    void testEditProfile_EmailNotFound_ShouldThrowException() {
//        when(userRepo.findByEmail("unknown@example.com")).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(UserNotFoundException.class, () -> {
//            userService.editProfile("unknown@example.com", testUsers);
//        });
//
//        assertEquals(exception.getMessage(),"Email not found: unknown@example.com");
//    }
}
