package com.alperProje.billing.controller;

import com.alperProje.billing.model.Bill;
import com.alperProje.billing.service.BillingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.List;

@RestController
@RequestMapping("api/v1/Billing")
@Slf4j
@AllArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @PostMapping("/createBilling/{saleId}")
    public void createBill(@PathVariable Integer saleId) throws JsonProcessingException {
        billingService.createBillBySaleId(saleId);
    }

    @GetMapping("/getAllBillings")
    public ResponseEntity<List<Bill>> getAllBillings(){
        return new ResponseEntity<>(billingService.findAll(), HttpStatus.OK);
    }

}
