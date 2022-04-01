package com.alperProje.repository;

import com.alperProje.model.Sale;
import com.alperProje.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Integer> {
    List<SaleItem> findAll();

    @Override
    List<SaleItem> findAllById(Iterable<Integer> integers);

    @Query(value = "select s from SaleItem s where s.sessionId=:sessionId")
    List<SaleItem> getSaleItemBySessionId(@Param("sessionId") Integer sessionId);

}
