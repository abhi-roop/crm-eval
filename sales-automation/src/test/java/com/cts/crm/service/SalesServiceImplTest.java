package com.cts.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.crm.model.Sales;
import com.cts.crm.repository.SalesRepository;

@ExtendWith(MockitoExtension.class)
class SalesServiceImplTest {

    @Mock
    private SalesRepository salesRepository;

    @InjectMocks
    private SalesServiceImpl salesService;

    private Sales sales;

    @BeforeEach
    void setUp() {
        sales = new Sales();
        sales.setOpportunityId(1L);
        sales.setCustomerId(100L);
        sales.setSalesStage("Prospecting");
        sales.setEstimatedValue(BigDecimal.valueOf(50000.00));
        sales.setClosingDate(LocalDateTime.now().plusDays(30));
        sales.setCreationDate(LocalDateTime.now());
        sales.setLastUpdatedDate(LocalDateTime.now());
    }

    @Test
    void testGetAllSales() {
        when(salesRepository.findAll()).thenReturn(Arrays.asList(sales));

        List<Sales> salesList = salesService.getAllSales();
        assertFalse(salesList.isEmpty());
        assertEquals(1, salesList.size());

        verify(salesRepository, times(1)).findAll();
    }

    @Test
    void testGetSalesById() {
        when(salesRepository.findById(1L)).thenReturn(Optional.of(sales));

        Optional<Sales> retrievedSales = salesService.getSalesById(1L);
        assertTrue(retrievedSales.isPresent());
        assertEquals("Prospecting", retrievedSales.get().getSalesStage());

        verify(salesRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateSales() {
        when(salesRepository.save(any(Sales.class))).thenReturn(sales);

        Sales createdSales = salesService.createSales(sales);
        assertNotNull(createdSales);
        assertEquals("Prospecting", createdSales.getSalesStage());

        verify(salesRepository, times(1)).save(any(Sales.class));
    }

    @Test
    void testUpdateSales() {
        when(salesRepository.findById(1L)).thenReturn(Optional.of(sales));
        when(salesRepository.save(any(Sales.class))).thenReturn(sales);

        Optional<Sales> updatedSales = salesService.updateSales(1L, sales);
        assertTrue(updatedSales.isPresent());
        assertEquals("Prospecting", updatedSales.get().getSalesStage());

        verify(salesRepository, times(1)).findById(1L);
        verify(salesRepository, times(1)).save(any(Sales.class));
    }

    @Test
    void testDeleteSales() {
        when(salesRepository.existsById(1L)).thenReturn(true);
        doNothing().when(salesRepository).deleteById(1L);

        assertDoesNotThrow(() -> salesService.deleteSales(1L));

        verify(salesRepository, times(1)).existsById(1L);
        verify(salesRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetSalesByCustomerId() {
        when(salesRepository.findByCustomerId(100L)).thenReturn(Arrays.asList(sales));

        List<Sales> salesList = salesService.getSalesByCustomerId(100L);
        assertFalse(salesList.isEmpty());

        verify(salesRepository, times(1)).findByCustomerId(100L);
    }

    @Test
    void testGetSalesBySalesStage() {
        when(salesRepository.findBySalesStage("Prospecting")).thenReturn(Arrays.asList(sales));

        List<Sales> salesList = salesService.getSalesBySalesStage("Prospecting");
        assertFalse(salesList.isEmpty());

        verify(salesRepository, times(1)).findBySalesStage("Prospecting");
    }

    @Test
    void testGetSalesByClosingDateBefore() {
        when(salesRepository.findByClosingDateBefore(any(LocalDateTime.class))).thenReturn(Arrays.asList(sales));

        List<Sales> salesList = salesService.getSalesByClosingDateBefore(LocalDateTime.now());
        assertFalse(salesList.isEmpty());

        verify(salesRepository, times(1)).findByClosingDateBefore(any(LocalDateTime.class));
    }

    @Test
    void testGetSalesByClosingDateAfter() {
        when(salesRepository.findByClosingDateAfter(any(LocalDateTime.class))).thenReturn(Arrays.asList(sales));

        List<Sales> salesList = salesService.getSalesByClosingDateAfter(LocalDateTime.now());
        assertFalse(salesList.isEmpty());

        verify(salesRepository, times(1)).findByClosingDateAfter(any(LocalDateTime.class));
    }

    @Test
    void testGetSalesByEstimatedValueGreaterThanEqual() {
        when(salesRepository.findByEstimatedValueGreaterThanEqual(any(BigDecimal.class))).thenReturn(Arrays.asList(sales));

        List<Sales> salesList = salesService.getSalesByEstimatedValueGreaterThanEqual(BigDecimal.valueOf(30000));
        assertFalse(salesList.isEmpty());

        verify(salesRepository, times(1)).findByEstimatedValueGreaterThanEqual(any(BigDecimal.class));
    }

    @Test
    void testGetSalesByEstimatedValueLessThanEqual() {
        when(salesRepository.findByEstimatedValueLessThanEqual(any(BigDecimal.class))).thenReturn(Arrays.asList(sales));

        List<Sales> salesList = salesService.getSalesByEstimatedValueLessThanEqual(BigDecimal.valueOf(70000));
        assertFalse(salesList.isEmpty());

        verify(salesRepository, times(1)).findByEstimatedValueLessThanEqual(any(BigDecimal.class));
    }
}
