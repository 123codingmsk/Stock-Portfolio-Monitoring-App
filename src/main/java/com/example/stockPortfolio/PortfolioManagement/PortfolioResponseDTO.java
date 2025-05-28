package com.example.stockPortfolio.PortfolioManagement;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioResponseDTO {
    private List<Portfolio> result;
    private int status;
    private String message;

    public PortfolioResponseDTO(List<Portfolio> result, int status, String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }
}
