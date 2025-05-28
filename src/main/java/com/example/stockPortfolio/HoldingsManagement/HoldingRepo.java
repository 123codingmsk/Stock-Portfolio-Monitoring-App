package com.example.stockPortfolio.HoldingsManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HoldingRepo extends JpaRepository<Holding, Long>{
    List<Holding> findByUserIdAndPortfolioId(Long userId, Long portfolioId);
    List<Holding> findByUserId(Long userId);
}
