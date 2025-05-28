package com.example.stockPortfolio.PortfolioManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByUserId(Long userId);
}
