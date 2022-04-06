package com.alperProje.billing.feign;

import com.alperProje.billing.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "PRODUCTS")
public interface ProductServiceFeignClient {
    @GetMapping("/api/v1/products/getPricesByProductIds/{productIds}")
    public List<Integer> getProductPricesByProductIds(@PathVariable("productIds") String productIds);
}
