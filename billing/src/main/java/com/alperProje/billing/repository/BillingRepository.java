package com.alperProje.billing.repository;

import com.alperProje.billing.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Bill, Integer> {

}
