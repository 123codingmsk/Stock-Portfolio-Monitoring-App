package com.example.stockPortfolio.HoldingsManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdAndPortfolioIdOrderByTransactionDateDesc(Long userId, Long portfolioId);
}
