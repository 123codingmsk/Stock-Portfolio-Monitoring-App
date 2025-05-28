package com.example.stockPortfolio.HoldingsManagement;

import lombok.Data;

@Data
public class StockDTO {
    private Long stockId;
    private String companyName;
    private String symbol;
    private String sector;
    private double CurrentPrice;
}
