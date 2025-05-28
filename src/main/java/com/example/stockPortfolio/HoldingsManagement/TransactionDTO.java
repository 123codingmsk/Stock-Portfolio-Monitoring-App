package com.example.stockPortfolio.HoldingsManagement;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long transactionId;
    private Long userId;
    private Long portfolioId;
    private String symbol;
    private int quantity;
    private double price;
    private String type;
    private LocalDateTime transactionDate;
    private Double gain;
    private Double gainPercentage;
    private Double loss;
    private Double lossPercentage;
}
