package com.example.stockPortfolio.AlertManagement;

import com.example.stockPortfolio.ExceptionManagement.StockDataUnavailableException;
import com.example.stockPortfolio.HoldingsManagement.StockDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {
    private static String API_URL = "https://x8ki-letl-twmt.n7.xano.io/api:GRubCYVh/srm_stock_mock_data";

    private static RestTemplate restTemplate = new RestTemplate();

    public static Map<String, StocksDTO> getAllStocks(){
        try {
            StocksDTO[] stocks = restTemplate.getForObject(API_URL, StocksDTO[].class);
            Map<String, StocksDTO> map = new HashMap<>();
            if (stocks != null) {
                for (StocksDTO s : stocks) {
                    map.put(s.getSymbol(), s);
                }
            }
            return map;
        } catch (RestClientException e) {
            throw new StockDataUnavailableException("Failed to fetch stock details from external API.");
        }
    }
}
