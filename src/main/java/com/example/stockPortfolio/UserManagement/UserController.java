package com.example.stockPortfolio.UserManagement;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name="1. User", description = "1st Controller")
public class UserController {

    //injecting the service class, methods inside this
    //we call it dependency injection
    @Autowired
    UserService userService;

    //api for register
    @PostMapping("/register")
    public UserModel register(@Valid @RequestBody UserModel userModel){
        return userService.register(userModel);
    }

    //api for login
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestDTO loginRequest){
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    //api for updating the profile
    @PutMapping("/updateProfile/{email}")
    public UserModel update(@Valid @PathVariable String email,@Valid @RequestBody UserModel userModel){
        return userService.editProfile(email, userModel);
    }
}
