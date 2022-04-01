package com.alperProje.products.controller;

import com.alperProje.products.model.Product;
import com.alperProje.products.request.ProductRegistrationRequest;
import com.alperProje.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseBody
    public void registerProduct(@RequestBody ProductRegistrationRequest productRegistrationRequest){
        log.info("Registered Product: " + productRegistrationRequest.toString());
        productService.registerProduct(productRegistrationRequest);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/getByIds/{productIds}")
    public ResponseEntity<List<Product>> getProductsById(@PathVariable List<Integer> productIds){
        return new ResponseEntity<>(productService.getProductsById(productIds), HttpStatus.OK);
    }

    @GetMapping("/getPricesByProductIds/{productIds}")
    public ResponseEntity<List<Integer>> getPricesByProductIds(@PathVariable List<Integer> productIds){
        return new ResponseEntity<>(productService.getPricesByProductId(productIds), HttpStatus.OK);
    }
}
