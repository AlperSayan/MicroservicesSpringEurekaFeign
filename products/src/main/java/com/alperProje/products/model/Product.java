package com.alperProje.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sequence")
    public Integer id;
    public String modelName;
    public Integer quantity;
    public Integer price;
}
