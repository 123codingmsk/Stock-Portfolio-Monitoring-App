package com.example.stockPortfolio.HoldingsManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/holdings")
@RequiredArgsConstructor
public class HoldingController {

    private final HoldingService holdingService;

    @PostMapping
    public ResponseEntity<ApiResponse<Transaction>> recordTransaction(@RequestBody TransactionDTO dto) {
        Transaction txn = new Transaction();
        txn.setUserId(dto.getUserId());
        txn.setPortfolioId(dto.getPortfolioId());
        txn.setSymbol(dto.getSymbol());
        txn.setQuantity(dto.getQuantity());
        txn.setPrice(dto.getPrice());
        txn.setTransactionDate(LocalDateTime.now());
        txn.setType(Transaction.TransactionType.valueOf(dto.getType().toUpperCase()));

        try {
            holdingService.processTransaction(txn);
            return ResponseEntity.ok(new ApiResponse<>(txn, 200, "Transaction recorded and holdings updated"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, 400, e.getMessage()));
        }
    }

    //all the buying and selling holdings
    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse<List<TransactionDTO>>> getTransactions(@RequestParam Long userId,
                                                                             @RequestParam Long portfolioId) {
        List<TransactionDTO> transactions = holdingService.getAllTransactions(userId, portfolioId);
        return ResponseEntity.ok(new ApiResponse<>(transactions, 200, "Transactions fetched successfully"));
    }

    //only bought stocks will come here, not after selling
    @GetMapping
    public ResponseEntity<ApiResponse<HoldingResponseDTO>> getHoldings(@RequestParam Long userId,
                                                                       @RequestParam Long portfolioId) {
        HoldingResponseDTO response = holdingService.getHoldingsWithDetails(userId, portfolioId);
        return ResponseEntity.ok(new ApiResponse<>(response, 200, "Holdings fetched successfully"));
    }

    //updating the current existed holding
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateHolding(@PathVariable Long id, @RequestBody HoldingUpdateDTO dto) {
        try {
            holdingService.updateHolding(id, dto);
            return ResponseEntity.ok(new ApiResponse<>("Holding updated successfully", 200, "Success"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>(null, 404, "Holding not found"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, 400, e.getMessage()));
        }
    }

    //deleting the existing holding
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteHolding(@PathVariable Long id) {
        try {
            holdingService.deleteHolding(id);
            return ResponseEntity.ok(new ApiResponse<>("Holding deleted successfully", 200, "Success"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>(null, 404, "Holding not found"));
        }
    }
}
