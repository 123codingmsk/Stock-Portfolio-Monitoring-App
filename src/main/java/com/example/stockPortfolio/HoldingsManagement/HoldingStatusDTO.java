package com.example.stockPortfolio.HoldingsManagement;

import lombok.Data;

@Data
public class HoldingStatusDTO {
    private String symbol;
    private String companyName;
    private String sector;
    private int quantity;
    private double buyPrice;
    private double currentPrice;
    private double gain;
    private double gainPercentage;
}
