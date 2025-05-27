package com.example.stockPortfolio.UserManagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//this class is for seperate usage while login for the user
//like user can only login using email and password, so need of name
@Data
public class LoginRequest {
    @NotBlank(message = "Email is required!")
    @Pattern(
            regexp = "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}",
            message = "Please enter a valid email address, like example@domain.com"
    )
    private String email;
    @NotBlank(message = "Password required!")
    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,}",
            message = "at least one letter, at least one digit, 6 or more characters")
    private String password;
}
