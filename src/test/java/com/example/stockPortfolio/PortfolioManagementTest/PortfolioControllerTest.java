package com.example.stockPortfolio.PortfolioManagementTest;

import com.example.stockPortfolio.PortfolioManagement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PortfolioControllerTest {

    // Create a fake PortfolioService
    @Mock
    private PortfolioService portfolioService;

    // Create a PortfolioController and inject the fake service
    @InjectMocks
    private PortfolioController portfolioController;

    // Sample portfolio for use in tests
    private Portfolio testPortfolio;

    @BeforeEach
    void setUp() {
        testPortfolio = new Portfolio();
        testPortfolio.setPortfolioId(1L);
        testPortfolio.setUserId(100L);
        testPortfolio.setPortfolioName("Test Portfolio Name That Is Long Enough To Pass Validation");
    }

    // Test: adding a portfolio (get only the Portfolio, not the ResponseEntity)
    @Test
    void testAddPortfolio() {
        // Tell the fake service to return testPortfolio when addPortfolio is called
        when(portfolioService.addPortfolio(any(Portfolio.class))).thenReturn(testPortfolio);

        // Call the controller and get only the Portfolio object from the response
        Portfolio result = portfolioController.addPortfolio(100L, testPortfolio).getBody();

        // Check that the Portfolio is not null and has the correct ID
        assertNotNull(result);
        assertEquals(1L, result.getPortfolioId());
    }

    // Test: get portfolios by user ID (get only the DTO body)
    @Test
    void testGetPortfoliosByUserId() {
        PortfolioResponseDTO responseDTO = new PortfolioResponseDTO(List.of(testPortfolio), 200, "Success");
        when(portfolioService.getPortfoliosByUserId(100L)).thenReturn(responseDTO);

        // Get only the DTO from the response
        PortfolioResponseDTO result = portfolioController.getPortfoliosByUserId(100L).getBody();

        assertNotNull(result);
        assertEquals(1, result.getResult().size());
        assertEquals(200, result.getStatus());
        assertEquals("Success", result.getMessage());
    }

    // Test: get all portfolios (get only the DTO body)
    @Test
    void testGetAllPortfolios() {
        PortfolioResponseDTO responseDTO = new PortfolioResponseDTO(List.of(testPortfolio), 200, "Success");
        when(portfolioService.getAllPortfolios()).thenReturn(responseDTO);

        // Get only the DTO from the response
        PortfolioResponseDTO result = portfolioController.getAllPortfolios().getBody();

        assertNotNull(result);
        assertEquals(1, result.getResult().size());
        assertEquals(200, result.getStatus());
        assertEquals("Success", result.getMessage());
    }
}
