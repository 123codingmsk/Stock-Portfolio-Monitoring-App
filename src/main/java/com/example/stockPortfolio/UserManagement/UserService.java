package com.example.stockPortfolio.UserManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//contains all the main logic
@Service
public class UserService {

    //it's like injecting the userRepo methods inside this class
    //behind the scenes, this is automatically creating an object
    @Autowired
    UserRepo userRepo;

    //registration of the user
    public UserModel register(UserModel userModel){
        if(userRepo.existsByEmail(userModel.getEmail())){
            throw new UserNotFoundException("User already exists in our database");
        }else{
            return userRepo.save(userModel);
        }
    }

    //login for the user
    public String login(String email, String password){
        UserModel user = userRepo.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("Email not present in our database. Please register"));

        if(!user.getPassword().equals(password)){
            throw new UserNotFoundException("Incorrect password!");
        }
        return "Login Successful!";
    }

    //user can edit the profile, if he wants
    public UserModel editProfile(String email, UserModel userModel){
        return userRepo.findByEmail(email)
                .map(existed->{
                    existed.setName(userModel.getName());
                    existed.setEmail(userModel.getEmail());
                    existed.setPassword(userModel.getPassword());
                    return userRepo.save(existed);
                }).orElseThrow(()->new UserNotFoundException("Email not found: "+email));
    }
}
