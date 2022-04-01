package com.alperProje.products.service;


import com.alperProje.products.Config.ProductConfig;
import com.alperProje.products.model.Product;
import com.alperProje.products.repository.ProductRepository;
import com.alperProje.products.request.ProductRegistrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate productConfig;

    public ProductService(ProductRepository productRepository, RestTemplate productConfig) {
        this.productRepository = productRepository;
        this.productConfig = productConfig;
    }


    public void registerProduct(ProductRegistrationRequest productReq){
        Product product = Product.builder().
                modelName(productReq.getModelName()).
                quantity(productReq.getQuantity()).
                price(productReq.getPrice()).
                build();
        productRepository.save(product);
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsById(List<Integer> ids){
        return productRepository.findAllById(ids);
    }

    public List<Integer> getPricesByProductId(List<Integer>ids){
        return getProductsById(ids).stream().map(Product::getPrice).collect(Collectors.toList());
    }

}
