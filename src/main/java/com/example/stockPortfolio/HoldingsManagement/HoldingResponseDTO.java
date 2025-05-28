package com.example.stockPortfolio.HoldingsManagement;

import lombok.Data;
import java.util.List;

@Data
public class HoldingResponseDTO {
    private List<HoldingStatusDTO> holdings;
    private double totalValue;
    private int status;
    private String message;

    public HoldingResponseDTO(List<HoldingStatusDTO> holdings, double totalValue, int status, String message) {
        this.holdings = holdings;
        this.totalValue = totalValue;
        this.status = status;
        this.message = message;
    }
}
