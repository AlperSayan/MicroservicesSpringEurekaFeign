package com.alperProje.billing.feign;


import com.alperProje.billing.dto.SaleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("SALES")
public interface SaleServiceFeignClient {

    @GetMapping("/api/v1/sales/getSaleBySessionId/{sessionId}")
    public SaleDTO getSaleBySessionId(@PathVariable("sessionId") String sessionId);
}
