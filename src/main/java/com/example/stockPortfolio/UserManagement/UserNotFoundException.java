package com.example.stockPortfolio.UserManagement;

//custom exception which extends runtimeException
public class UserNotFoundException extends RuntimeException{
    private String message;

    UserNotFoundException(String message){
        super(message);
    }
}
