package com.example.stockPortfolio.HoldingsManagement;

import com.example.stockPortfolio.ExceptionManagement.StockDataUnavailableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class StockPriceService{
    private static String API_URL = "https://x8ki-letl-twmt.n7.xano.io/api:GRubCYVh/srm_stock_mock_data";

    private static RestTemplate restTemplate = new RestTemplate();

    public static Map<String, StockDTO> getStockDetailsMap(){
        try {
            StockDTO[] stocks = restTemplate.getForObject(API_URL, StockDTO[].class);
            Map<String, StockDTO> map = new HashMap<>();
            if (stocks != null) {
                for (StockDTO stock : stocks) {
                    map.put(stock.getSymbol(), stock);
                }
            }
            return map;
        } catch (RestClientException e) {
            throw new StockDataUnavailableException("Failed to fetch stock details from external API.");
        }
    }
}
