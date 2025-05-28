package com.example.stockPortfolio.ExceptionManagement;

import lombok.Data;
import java.time.LocalDateTime;

// this class is useful to show the errors while exception occurs
@Data
public class ErrorClass{
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorClass(int s, String msg, LocalDateTime t){
        this.status = s;
        this.message = msg;
        this.timestamp = t;
    }
}
