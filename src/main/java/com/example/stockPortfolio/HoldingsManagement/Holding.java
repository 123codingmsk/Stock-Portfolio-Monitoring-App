package com.example.stockPortfolio.HoldingsManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Holding{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holdingId;
    @NotNull(message = "User Id is Required" )
    private Long userId;
    @NotNull(message = "Portfolio Id is Required" )
    private Long portfolioId;
    @NotBlank(message = "Symbol is Required")
    private String symbol;
    @NotNull(message = "Quantity is Required" )
    private int quantity;
    @DecimalMin(value = "0.01",inclusive = true,message = "Value must be greater than or equal to 0.01")
    private double buyPrice;

    public Holding(long l, long l1, long l2, String aapl, int i, double v) {
    }

    public Holding() {

    }
}
