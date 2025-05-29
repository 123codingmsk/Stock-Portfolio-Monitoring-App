package com.example.stockPortfolio.AlertManagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepo extends JpaRepository<Alert, Long> {
    AlertDTO findBySymbol(String symbol);
}
