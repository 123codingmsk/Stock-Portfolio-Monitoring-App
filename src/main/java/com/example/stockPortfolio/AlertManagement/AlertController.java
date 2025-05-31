package com.example.stockPortfolio.AlertManagement;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@Tag(name="4. Alert", description = "4th Controller, Alerts")
public class AlertController {

    @Autowired
    public AlertService alertService;

    @Autowired
    private AlertRepo alertRepo;

    @GetMapping
    public List<Alert> getAlert(){
        return alertRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<AlertDTO> addAlert(@RequestBody Alert alert){
        AlertDTO alertDTO = alertService.addAlert(alert);
        return new ResponseEntity<>(alertDTO, HttpStatusCode.valueOf(alertDTO.getStatus()));
    }
}
