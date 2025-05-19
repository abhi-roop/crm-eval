package com.cts.crm.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cts.crm.model.Sales;
import com.cts.crm.service.SalesService;

@RestController
@RequestMapping("/api/sales/opportunities") // Keeping the API endpoint name consistent with the resource
public class SalesAutomationController {

    private final SalesService salesService;

  //  @Autowired
    public SalesAutomationController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sales getSalesById(@PathVariable Long id) {
        return salesService.getSalesById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales opportunity with ID " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sales createSales(@RequestBody Sales sales) {
        return salesService.createSales(sales);
    }

    @PutMapping("/{id}")
    public Sales updateSales(@PathVariable Long id, @RequestBody Sales updatedSales) {
        return salesService.updateSales(id, updatedSales)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales opportunity with ID " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSales(@PathVariable Long id) {
        salesService.deleteSales(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Sales> getSalesByCustomerId(@PathVariable Long customerId) {
        return salesService.getSalesByCustomerId(customerId);
    }

    @GetMapping("/stage/{salesStage}")
    public List<Sales> getSalesBySalesStage(@PathVariable String salesStage) {
        return salesService.getSalesBySalesStage(salesStage);
    }

    @GetMapping("/closing-before")
    public List<Sales> getSalesByClosingDateBefore(@RequestParam String closingDate) {
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(closingDate);
            return salesService.getSalesByClosingDateBefore(parsedDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for closingDate");
        }
    }

    @GetMapping("/closing-after")
    public List<Sales> getSalesByClosingDateAfter(@RequestParam String closingDate) {
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(closingDate);
            return salesService.getSalesByClosingDateAfter(parsedDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for closingDate");
        }
    }

    @GetMapping("/value-greater-equal")
    public List<Sales> getSalesByEstimatedValueGreaterThanEqual(@RequestParam BigDecimal value) {
        return salesService.getSalesByEstimatedValueGreaterThanEqual(value);
    }

    @GetMapping("/value-less-equal")
    public List<Sales> getSalesByEstimatedValueLessThanEqual(@RequestParam BigDecimal value) {
        return salesService.getSalesByEstimatedValueLessThanEqual(value);
    }
}