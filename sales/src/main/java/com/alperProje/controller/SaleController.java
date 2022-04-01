package com.alperProje.controller;

import com.alperProje.model.SaleItem;
import com.alperProje.request.SaleRequest;
import com.alperProje.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/sales")
@Slf4j
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping(value = "/createSale", consumes = "application/json")
    public void createSale(@RequestBody SaleRequest saleRequest){
        saleService.createSale(saleRequest);
    }

    @GetMapping("/getAllSales")
    public ResponseEntity<HashMap<Integer, Object>> getAllSalesByEachSale(){
        return new ResponseEntity<>(saleService.getAllSales(), HttpStatus.OK);
    }

    @GetMapping("/getSaleBySessionId/{sessionId}")
    public ResponseEntity<HashMap<String, Object>> getSaleBySessionId(@PathVariable Integer sessionId){
        return new ResponseEntity<>(saleService.getSaleById(sessionId), HttpStatus.OK);
    }
}
