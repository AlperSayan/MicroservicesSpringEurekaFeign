package com.alperProje.products.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductRegistrationRequest {
    private String modelName;
    private Integer quantity;
    private Integer price;
}
