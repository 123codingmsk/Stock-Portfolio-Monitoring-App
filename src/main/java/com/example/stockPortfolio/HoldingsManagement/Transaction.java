package com.example.stockPortfolio.HoldingsManagement;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @NotNull
    private Long userId;
    @NotNull
    private Long portfolioId;
    @NotBlank
    private String symbol;
    @NotNull
    private int quantity;
    @DecimalMin(value = "0.01")
    private double price;
    @NotNull
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public enum TransactionType {
        BUY, SELL
    }
}
