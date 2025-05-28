package com.example.stockPortfolio.ExceptionManagement;

public class StockDataUnavailableException extends RuntimeException {
    public StockDataUnavailableException(String message) {
        super(message);
    }
}