package com.example.stockPortfolio.PortfolioManagementTest;

import com.example.stockPortfolio.ExceptionManagement.ResourceNotFoundException;
import com.example.stockPortfolio.PortfolioManagement.Portfolio;
import com.example.stockPortfolio.PortfolioManagement.PortfolioRepo;
import com.example.stockPortfolio.PortfolioManagement.PortfolioResponseDTO;
import com.example.stockPortfolio.PortfolioManagement.PortfolioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class) //Initialised the use of Mockito in here in this class
public class PortfolioServiceTest {

    @Mock
    PortfolioRepo portfolioRepo;

    @InjectMocks
    PortfolioService portfolioService;

    private Portfolio portfolio1 ;

    @BeforeEach
    void setPortfolio(){
        portfolio1  = new Portfolio(); // we created a new instance over here

        portfolio1.setPortfolioId(1l);
        portfolio1.setUserId(100l);
    }
    @Test
    void testAddPortfolio(){
        Mockito.when(portfolioRepo.save(portfolio1)).thenReturn(portfolio1);

        Portfolio result = portfolioService.addPortfolio(portfolio1);

        assertNotNull(result);
        Assertions.assertEquals(portfolio1.getUserId(),result.getUserId());
        verify(portfolioRepo).save(portfolio1);
    }


    @Test
    public void testGetPortfoliosByUserId_Success() {

        List<Portfolio> portfolios = Collections.singletonList(portfolio1);
        Mockito.when(portfolioRepo.findByUserId(123L)).thenReturn(portfolios);

        PortfolioResponseDTO response = portfolioService.getPortfoliosByUserId(123L);

        assertNotNull(response);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Your portfolios are brought successfully!", response.getMessage());
    }

    @Test
    public void testGetPortfoliosByUserId_Empty() {
        Mockito.when(portfolioRepo.findByUserId(123L)).thenReturn(Collections.emptyList());

        Exception ex = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            portfolioService.getPortfoliosByUserId(123L);
        });

        Assertions.assertEquals("No portfolios found for user ID: 123", ex.getMessage());
    }

    @Test
    public void testGetAllPortfolios_Success() {
        List<Portfolio> portfolios = Collections.singletonList(portfolio1);
        Mockito.when(portfolioRepo.findAll()).thenReturn(portfolios);

        PortfolioResponseDTO response = portfolioService.getAllPortfolios();

        assertNotNull(response);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("All Portfolios brought successfully", response.getMessage());
    }

    @Test
    public void testGetAllPortfolios_Empty() {
        Mockito.when(portfolioRepo.findAll()).thenReturn(Collections.emptyList());

        Exception ex = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            portfolioService.getAllPortfolios();
        });

        Assertions.assertEquals("No portfolios found!", ex.getMessage());

    }
}