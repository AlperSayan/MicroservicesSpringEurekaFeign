package com.alperProje.repository;

import com.alperProje.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findAll();

    @Override
    List<Sale> findAllById(Iterable<Integer> integers);

    @Override
    Optional<Sale> findById(Integer integer);

    @Query("Select s.sessionId from Sale s")
    List<Integer> getAllSessionId();

    @Query("Select s.soldTo from Sale s")
    List<String> getAllSoldTo();

    @Query("Select s.soldTo from Sale s where s.sessionId=:sessionId")
    String getSoldToBySessionId(@Param("sessionId") Integer sessionId);
}
