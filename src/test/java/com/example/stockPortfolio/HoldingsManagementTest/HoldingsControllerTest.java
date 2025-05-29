package com.example.stockPortfolio.HoldingsManagementTest;

import com.example.stockPortfolio.HoldingsManagement.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HoldingsControllerTest {

    @Mock
    HoldingService holdingService;

    @InjectMocks
    HoldingController holdingController;

    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks and inject all mock objects into our HoldingController instance
    }

    @Test
    void Test_recordTransaction_Success(){
        TransactionDTO dto = new TransactionDTO();

        dto.setUserId(1L);
        dto.setPortfolioId(2L);
        dto.setSymbol("AAPL");
        dto.setQuantity(5);
        dto.setPrice(100.0);
        dto.setType("BUY");

// It returns us a body ("msg") and also our status code like (200 Ok)

        ResponseEntity <ApiResponse<Transaction>> response = holdingController.recordTransaction(dto);

//Checking part
        Assertions.assertEquals(200 ,response.getStatusCodeValue());
        Assertions.assertEquals(
                "Transaction recorded and holdings updated",
                response.getBody().getMessage().replaceAll("\\.$", "")
        );
        ;

        verify(holdingService, times(1)).processTransaction(any(Transaction.class));

    }
    @Test
    void test_RecordTransaction_Failure(){
        TransactionDTO dto = new TransactionDTO();

        dto.setUserId(1L);
        dto.setPortfolioId(2L);
        dto.setSymbol("AAPL");
        dto.setQuantity(5);
        dto.setPrice(100.0);
        dto.setType("BUY");

//Throws the message when our selling transaction processes
        Mockito.doThrow(new IllegalArgumentException("Insufficient quantity to sell.")).when(holdingService).processTransaction(any(Transaction.class));


        ResponseEntity <ApiResponse<Transaction>> response = holdingController.recordTransaction(dto);

        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("Insufficient quantity to sell.", response.getBody().getMessage());

    }

    @Test
    void test_getTransaction(){
        TransactionDTO txn = new TransactionDTO();

        txn.setTransactionId(1L);
        txn.setUserId(1L);
        txn.setPortfolioId(2L);
        txn.setSymbol("AAPL");
        txn.setQuantity(10);
        txn.setPrice(150.0);
        txn.setType("BUY");
        txn.setTransactionDate(LocalDateTime.now());

        when(holdingService.getAllTransactions(1l,2l)).thenReturn(List.of(txn));


        ResponseEntity <ApiResponse<List<TransactionDTO>>> response = holdingController.getTransactions(1l,2l);
        Assertions.assertEquals(200,response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().getResult().size() == 1); // ✅ This is correct

    }
    @Test
    void test_getHoldings(){

        HoldingResponseDTO dto = new HoldingResponseDTO(Collections.emptyList(), 0.0,200,"OK");

        when(holdingService.getHoldingsWithDetails(1l,2l)).thenReturn(dto);

        ResponseEntity <ApiResponse<HoldingResponseDTO>> response = holdingController.getHoldings(1l,2l);
        Assertions.assertEquals(200,response.getStatusCodeValue());
        Assertions.assertEquals("Holdings fetched successfully", response.getBody().getMessage());
    }
}