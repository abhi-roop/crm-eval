package com.cts.crm.service;

import com.cts.crm.model.Sales;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SalesService {
    List<Sales> getAllSales();
    Optional<Sales> getSalesById(Long id);
    Sales createSales(Sales sales);
    Optional<Sales> updateSales(Long id, Sales sales);
    void deleteSales(Long id);
    List<Sales> getSalesByCustomerId(Long customerId);
    List<Sales> getSalesBySalesStage(String salesStage);
    List<Sales> getSalesByClosingDateBefore(LocalDateTime closingDate);
    List<Sales> getSalesByClosingDateAfter(LocalDateTime closingDate);
    List<Sales> getSalesByEstimatedValueGreaterThanEqual(BigDecimal value);
    List<Sales> getSalesByEstimatedValueLessThanEqual(BigDecimal value);
}