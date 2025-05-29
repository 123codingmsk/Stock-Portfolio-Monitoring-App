package com.example.stockPortfolio.AlertManagementTest;

import com.example.stockPortfolio.AlertManagement.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.mockito.MockitoAnnotations.openMocks;

public class AlertControllerTest {

    @Mock
    public AlertRepo alertRepo;

    @Mock
    public AlertService alertService;

    @InjectMocks
    public AlertController alertcontroller;

    @BeforeEach
    void setUp(){
        openMocks(this); //it will initialize the mock everytime before every test
    }

    @Test
    void test_getAlert(){

        Alert a1=new Alert();
        a1.setId(1l);
        a1.setUserId(1l);
        a1.setSymbol("XYZ");
        a1.setGainOrLoss("GAIN");
        a1.setTargetPrice("120");

        Alert a2 = new Alert();
        a2.setId(1l);
        a2.setUserId(1l);
        a2.setSymbol("XYZ");
        a2.setGainOrLoss("GAIN");
        a2.setTargetPrice("120");

        List<Alert> list_of_Alerts = Arrays.asList(a1,a2);

        Mockito.when(alertRepo.findAll()).thenReturn(list_of_Alerts); //Testing the data present or not

        List<Alert> actual_alerts =alertcontroller.getAlert();

        Assertions.assertEquals(2,actual_alerts.size());
        Mockito.verify(alertRepo,Mockito.times(1)).findAll();
    }

    @Test
    void test_addAlert(){
        Alert new_alert = new Alert();
        new_alert.setId(1L);
        new_alert.setUserId(1L);
        new_alert.setSymbol("XYZ");
        new_alert.setGainOrLoss("GAIN");
        new_alert.setTargetPrice("120");

        AlertDTO alertDTO = new AlertDTO();
        alertDTO.setMessage("Alert Triggered -> Gain of 10.00%");
        alertDTO.setStatus(200);
        alertDTO.setLocalDateTime(LocalDateTime.now());

        Mockito.when(alertService.addAlert(new_alert)).thenReturn(alertDTO);

        ResponseEntity<?> res = alertcontroller.addAlert(new_alert);

        Assertions.assertNotNull(res);
        Assertions.assertEquals(200, res.getStatusCode().value());

        AlertDTO responseBody = (AlertDTO) res.getBody();
        Assertions.assertEquals("Alert Triggered -> Gain of 10.00%", responseBody.getMessage());
        Assertions.assertEquals(200, responseBody.getStatus());
        Assertions.assertNotNull(responseBody.getLocalDateTime());

        Mockito.verify(alertService, Mockito.times(1)).addAlert(new_alert);
    }
}
