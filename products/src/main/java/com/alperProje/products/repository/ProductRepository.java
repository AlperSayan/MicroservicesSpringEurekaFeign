package com.alperProje.products.repository;

import com.alperProje.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    @Override
    List<Product> findAllById(Iterable<Integer> integers);


}
