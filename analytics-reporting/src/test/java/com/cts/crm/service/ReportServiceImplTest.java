package com.cts.crm.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.cts.crm.dto.CustomerData;
import com.cts.crm.dto.MarketingAutomation;
import com.cts.crm.dto.SalesAutomation;
import com.cts.crm.feignclient.CustomerClient;
import com.cts.crm.feignclient.MarketingClient;
import com.cts.crm.feignclient.SalesClient;
import com.cts.crm.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private CustomerClient customerClient;

    @Mock
    private SalesClient salesClient;

    @Mock
    private MarketingClient marketingClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ReportServiceImpl reportService;

    private CustomerData customer;
    private MarketingAutomation marketingAutomation;
    private SalesAutomation salesAutomation;

    @BeforeEach
    void setUp() {
        customer = new CustomerData(1L, "John Doe", "john@example.com", "1234567890", "New York",
                Arrays.asList("Product A", "Product B"), null, "North America", "Tech Enthusiast", "Frequent Buyer");

        marketingAutomation = new MarketingAutomation(10L, "Spring Campaign", null, null);
        salesAutomation = new SalesAutomation(100L, "Prospecting", null, null, null, null);

        // Ensure mock services return non-null data to prevent NullPointerException
        when(customerClient.getAllCustomers()).thenReturn(Arrays.asList(customer));
        when(marketingClient.getAllCampaigns()).thenReturn(Arrays.asList(marketingAutomation));
        when(salesClient.getAllSales()).thenReturn(Arrays.asList(salesAutomation));
    }

    @Test
    void testGetAllCustomers() {
        List<CustomerData> customers = reportService.getAllCustomers();
        assertNotNull(customers, "Customer list should not be null");
        assertFalse(customers.isEmpty(), "Customer list should not be empty");
        assertEquals("John Doe", customers.get(0).getName());

        verify(customerClient, times(1)).getAllCustomers();
    }

    @Test
    void testGetAllCampaigns() {
        List<MarketingAutomation> campaigns = reportService.getAllCampaigns();
        assertNotNull(campaigns, "Campaigns list should not be null");
        assertFalse(campaigns.isEmpty(), "Campaigns list should not be empty");
        assertEquals("Spring Campaign", campaigns.get(0).getName());

        verify(marketingClient, times(1)).getAllCampaigns();
    }

    @Test
    void testGetAllSales() {
        List<SalesAutomation> sales = reportService.getAllSales();
        assertNotNull(sales, "Sales list should not be null");
        assertFalse(sales.isEmpty(), "Sales list should not be empty");
        assertEquals("Prospecting", sales.get(0).getSalesStage());

        verify(salesClient, times(1)).getAllSales();
    }
}
