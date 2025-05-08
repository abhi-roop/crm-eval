package com.cts.crm.service;

import com.cts.crm.exception.SalesNotFoundException;
import com.cts.crm.model.Sales;
import com.cts.crm.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Override
    public Optional<Sales> getSalesById(Long id) {
        return salesRepository.findById(id);
    }

    @Override
    public Sales createSales(Sales sales) {
        sales.setCreationDate(LocalDateTime.now());
        sales.setLastUpdatedDate(LocalDateTime.now());
        return salesRepository.save(sales);
    }

    @Override
    public Optional<Sales> updateSales(Long id, Sales sales) {
        return salesRepository.findById(id)
                .map(existingSales -> {
                    existingSales.setCustomerId(sales.getCustomerId());
                    existingSales.setSalesStage(sales.getSalesStage());
                    existingSales.setEstimatedValue(sales.getEstimatedValue());
                    existingSales.setClosingDate(sales.getClosingDate());
                    existingSales.setLastUpdatedDate(LocalDateTime.now());
                    return Optional.of(salesRepository.save(existingSales));
                })
                .orElseThrow(() -> new SalesNotFoundException("Sales opportunity with ID " + id + " not found"));
    }

    @Override
    public void deleteSales(Long id) {
        if (!salesRepository.existsById(id)) {
            throw new SalesNotFoundException("Sales opportunity with ID " + id + " not found");
        }
        salesRepository.deleteById(id);
    }

    @Override
    public List<Sales> getSalesByCustomerId(Long customerId) {
        return salesRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Sales> getSalesBySalesStage(String salesStage) {
        return salesRepository.findBySalesStage(salesStage);
    }

    @Override
    public List<Sales> getSalesByClosingDateBefore(LocalDateTime closingDate) {
        return salesRepository.findByClosingDateBefore(closingDate);
    }

    @Override
    public List<Sales> getSalesByClosingDateAfter(LocalDateTime closingDate) {
        return salesRepository.findByClosingDateAfter(closingDate);
    }

    @Override
    public List<Sales> getSalesByEstimatedValueGreaterThanEqual(BigDecimal value) {
        return salesRepository.findByEstimatedValueGreaterThanEqual(value);
    }

    @Override
    public List<Sales> getSalesByEstimatedValueLessThanEqual(BigDecimal value) {
        return salesRepository.findByEstimatedValueLessThanEqual(value);
    }
}