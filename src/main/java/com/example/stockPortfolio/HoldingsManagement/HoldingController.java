package com.example.stockPortfolio.HoldingsManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class HoldingController {

    private final HoldingService holdingService;

    @PostMapping
    public ResponseEntity<String> recordTransaction(@RequestBody TransactionDTO dto) {
        Transaction txn = new Transaction();
        txn.setUserId(dto.getUserId());
        txn.setPortfolioId(dto.getPortfolioId());
        txn.setSymbol(dto.getSymbol());
        txn.setQuantity(dto.getQuantity());
        txn.setPrice(dto.getPrice());
        txn.setTransactionDate(LocalDateTime.now());
        txn.setType(Transaction.TransactionType.valueOf(dto.getType().toUpperCase()));

        try{
            holdingService.processTransaction(txn);
            return ResponseEntity.ok("Transaction recorded and holdings updated.");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //all the buying and selling holdings
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getTransactions(@RequestParam Long userId,
                                                                @RequestParam Long portfolioId) {
        return ResponseEntity.ok(holdingService.getAllTransactions(userId, portfolioId));
    }

    //only bought stocks will come here, not after selling
    @GetMapping("/holdings")
    public ResponseEntity<HoldingResponseDTO> getHoldings(@RequestParam Long userId,
                                                          @RequestParam Long portfolioId) {
        return ResponseEntity.ok(holdingService.getHoldingsWithDetails(userId, portfolioId));
    }
}
