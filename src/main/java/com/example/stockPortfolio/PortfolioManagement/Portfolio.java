package com.example.stockPortfolio.PortfolioManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;
    @NotNull(message = "userID is Required!")
    private Long userId;
    @NotBlank(message = "PortfolioName is Required!")
    @Size(min = 60)
    private String portfolioName;
}