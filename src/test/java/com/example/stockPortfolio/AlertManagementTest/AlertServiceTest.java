package com.example.stockPortfolio.AlertManagementTest;

import com.example.stockPortfolio.AlertManagement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Add these imports for static mocking
import static org.mockito.Mockito.mockStatic;
import org.mockito.MockedStatic;

/**
 * Test class for AlertService using static mocking for StockService.getAllStocks().
 */
@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {

    @Mock
    private AlertRepo alertRepo;

    // No need to mock StockService since getAllStocks is static

    @InjectMocks
    private AlertService alertService;

    private Alert testAlert;
    private StocksDTO testStocksDTO;
    private Map<String, StocksDTO> stocksMap;

    @BeforeEach
    public void setUp() {
        testAlert = new Alert();
        testAlert.setSymbol("AAPL");
        testAlert.setTargetPrice("150.0");
        testAlert.setGainOrLoss(null);

        testStocksDTO = new StocksDTO();
        testStocksDTO.setSymbol("AAPL");
        testStocksDTO.setCurrentPrice(165.0);

        stocksMap = new HashMap<>();
        stocksMap.put("AAPL", testStocksDTO);
    }

    @Test
    public void testAddAlert_Success_AlertTriggered() {
        try (MockedStatic<StockService> mockedStatic = mockStatic(StockService.class)) {
            mockedStatic.when(StockService::getAllStocks).thenReturn(stocksMap);
            when(alertRepo.save(any(Alert.class))).thenReturn(testAlert);

            AlertDTO result = alertService.addAlert(testAlert);

            assertNotNull(result);
            assertTrue(result.getMessage().contains("Alert Triggered"));
            assertEquals(HttpStatus.OK, result.getHttpStatus());
            assertEquals(HttpStatus.OK.value(), result.getStatus());
            assertNotNull(result.getLocalDateTime());

            verify(alertRepo).save(any(Alert.class));
        }
    }

    @Test
    public void testAddAlert_Success_AlertSavedConditionNotMet() {
        testStocksDTO.setCurrentPrice(140.0);

        try (MockedStatic<StockService> mockedStatic = mockStatic(StockService.class)) {
            mockedStatic.when(StockService::getAllStocks).thenReturn(stocksMap);
            when(alertRepo.save(any(Alert.class))).thenReturn(testAlert);

            AlertDTO result = alertService.addAlert(testAlert);

            assertNotNull(result);
            assertTrue(result.getMessage().contains("Alert Triggered"));
            assertEquals(HttpStatus.OK, result.getHttpStatus());
            assertEquals(HttpStatus.OK.value(), result.getStatus());
            verify(alertRepo).save(any(Alert.class));
        }
    }

    // Your requested static mocking test for symbol not found:
    @Test
    public void testAddAlert_SymbolNotFound_ThrowsException() {
        try (MockedStatic<StockService> mockedStatic = mockStatic(StockService.class)) {
            mockedStatic.when(StockService::getAllStocks).thenReturn(new HashMap<>());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                alertService.addAlert(testAlert);
            });

            assertEquals("Stock symbol not found in the available stock data.", exception.getMessage());
            verify(alertRepo, never()).save(any(Alert.class));
        }
    }
}