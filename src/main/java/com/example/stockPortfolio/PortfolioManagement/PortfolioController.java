package com.example.stockPortfolio.PortfolioManagement;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolios")
@Tag(name="2. Portfolio", description = "2nd Controller, After User Register & Login")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/{userId}")
    public ResponseEntity<Portfolio> addPortfolio(@PathVariable Long userId, @RequestBody Portfolio portfolio) {
        portfolio.setUserId(userId);
        Portfolio saved = portfolioService.addPortfolio(portfolio   );
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PortfolioResponseDTO> getPortfoliosByUserId(@PathVariable Long userId) {
        PortfolioResponseDTO response= portfolioService.getPortfoliosByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PortfolioResponseDTO> getAllPortfolios(){
        PortfolioResponseDTO response = portfolioService.getAllPortfolios();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
