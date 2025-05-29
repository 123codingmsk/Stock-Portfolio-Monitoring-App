package com.example.stockPortfolio.AlertManagement;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class AlertDTO {
    private String message;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    private int status;
}
