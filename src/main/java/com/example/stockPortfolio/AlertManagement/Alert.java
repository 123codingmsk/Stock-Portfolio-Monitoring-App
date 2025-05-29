package com.example.stockPortfolio.AlertManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @NotBlank(message = "Symbol should not be blank!")
    private String symbol;
    @NotBlank(message = "Enter the target price")
    private String TargetPrice;
    private String gainOrLoss;
}
