package com.example.stockPortfolio.HoldingsManagement;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private T result;
    private int status;
    private String message;

    public ApiResponse() {}

    public ApiResponse(T result, int status, String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }
}

