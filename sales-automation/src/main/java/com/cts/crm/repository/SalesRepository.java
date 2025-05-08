package com.cts.crm.repository;

import com.cts.crm.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findByCustomerId(Long customerId);
    List<Sales> findBySalesStage(String salesStage);
    List<Sales> findByClosingDateBefore(LocalDateTime closingDate);
    List<Sales> findByClosingDateAfter(LocalDateTime closingDate);
    List<Sales> findByEstimatedValueGreaterThanEqual(BigDecimal value);
    List<Sales> findByEstimatedValueLessThanEqual(BigDecimal value);
}