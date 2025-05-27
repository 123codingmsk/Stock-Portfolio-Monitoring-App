package com.example.stockPortfolio.UserManagement;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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
    public String login(@Valid @RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    //api for updating the profile
    @PutMapping("/updateProfile/{email}")
    public UserModel update(@Valid @PathVariable String email,@Valid @RequestBody UserModel userModel){
        return userService.editProfile(email, userModel);
    }
}
