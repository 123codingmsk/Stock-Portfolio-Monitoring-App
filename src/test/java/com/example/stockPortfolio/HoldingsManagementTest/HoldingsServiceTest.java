package com.example.stockPortfolio.HoldingsManagementTest;

import com.example.stockPortfolio.HoldingsManagement.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Service
public class HoldingsServiceTest {

    @Mock
    private HoldingRepo holdingRepo;

    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private HoldingService holdingService;

    @Test
    void test_buy_Transaction_NewHolding() {
        Transaction txn = new Transaction();
        txn.setType(Transaction.TransactionType.BUY);
        txn.setTransactionDate(LocalDateTime.now());
        txn.setUserId(1L);
        txn.setPortfolioId(1L);
        txn.setSymbol("AAPL");
        txn.setQuantity(10);
        txn.setPrice(100.0);

        when(holdingRepo.findByUserIdAndPortfolioId(1L, 1L)).thenReturn(Collections.emptyList());

        holdingService.processTransaction(txn);

        verify(holdingRepo).save(any(Holding.class));
        verify(transactionRepo).save(txn);
    }

    @Test
    void testProcessBuyTransaction_ExistingHolding() {
        Holding existingHolding = new Holding();
        existingHolding.setHoldingId(1L);
        existingHolding.setUserId(1L);
        existingHolding.setPortfolioId(1L);
        existingHolding.setSymbol("AAPL");
        existingHolding.setQuantity(10);
        existingHolding.setBuyPrice(100.0);

        Transaction txn = new Transaction();
        txn.setUserId(1L);
        txn.setPortfolioId(1L);
        txn.setSymbol("AAPL");
        txn.setQuantity(10);
        txn.setPrice(100.0);
        txn.setType(Transaction.TransactionType.BUY);
        txn.setTransactionDate(LocalDateTime.now());

        when(holdingRepo.findByUserIdAndPortfolioId(1L, 1L)).thenReturn(List.of(existingHolding));

        holdingService.processTransaction(txn);

        verify(holdingRepo).save(any(Holding.class));
        verify(transactionRepo).save(txn);
    }

    @Test
    void testProcessSellTransaction_SufficientQuantity() {
        Holding existingHolding = new Holding();
        existingHolding.setHoldingId(1L);
        existingHolding.setUserId(1L);
        existingHolding.setPortfolioId(1L);
        existingHolding.setSymbol("AAPL");
        existingHolding.setQuantity(15);
        existingHolding.setBuyPrice(100.0);

        Transaction txn = new Transaction();
        txn.setUserId(1L);
        txn.setPortfolioId(1L);
        txn.setSymbol("AAPL");
        txn.setQuantity(10);
        txn.setPrice(100.0);
        txn.setType(Transaction.TransactionType.SELL);
        txn.setTransactionDate(LocalDateTime.now());

        when(holdingRepo.findByUserIdAndPortfolioId(1L, 1L)).thenReturn(List.of(existingHolding));

        holdingService.processTransaction(txn);

        verify(holdingRepo).save(any(Holding.class));
        verify(transactionRepo).save(txn);
    }

    @Test
    void testProcessSellTransaction_InsufficientQuantity() {
        Holding existingHolding = new Holding();
        existingHolding.setHoldingId(1L);
        existingHolding.setUserId(1L);
        existingHolding.setPortfolioId(1L);
        existingHolding.setSymbol("AAPL");
        existingHolding.setQuantity(5);
        existingHolding.setBuyPrice(100.0);

        Transaction txn = new Transaction();
        txn.setUserId(1L);
        txn.setPortfolioId(1L);
        txn.setSymbol("AAPL");
        txn.setQuantity(10);
        txn.setPrice(100.0);
        txn.setType(Transaction.TransactionType.SELL);
        txn.setTransactionDate(LocalDateTime.now());

        when(holdingRepo.findByUserIdAndPortfolioId(1L, 1L)).thenReturn(List.of(existingHolding));


        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> holdingService.processTransaction(txn)
        );
        assertEquals("Insufficient quantity to sell.", thrown.getMessage());

        verify(transactionRepo, never()).save(any());
        verify(holdingRepo, never()).save(any());
    }

    @Test
    void testGetAllTransactions() {
        when(transactionRepo.findByUserIdAndPortfolioIdOrderByTransactionDateDesc(1L, 1L))
                .thenReturn(Collections.emptyList());

        List<TransactionDTO> result = holdingService.getAllTransactions(1L, 1L);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllTransactions_WithData() {
        Transaction txn = new Transaction();
        txn.setTransactionId(1L);
        txn.setUserId(1L);
        txn.setPortfolioId(1L);
        txn.setSymbol("AAPL");
        txn.setQuantity(5);
        txn.setPrice(150.0);
        txn.setType(Transaction.TransactionType.BUY);
        txn.setTransactionDate(LocalDateTime.now());

        when(transactionRepo.findByUserIdAndPortfolioIdOrderByTransactionDateDesc(1L, 1L))
                .thenReturn(List.of(txn));

        List<TransactionDTO> result = holdingService.getAllTransactions(1L, 1L);

        Assertions.assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).getSymbol());
        assertEquals(5, result.get(0).getQuantity());
    }
}