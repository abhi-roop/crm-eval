package com.cts.crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// ... (existing imports)

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService; // Assuming your implementation will be CustomerServiceImpl

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCustomers_shouldReturnEmptyList_whenNoCustomersExist() {
        // Arrange
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Customer> actualCustomers = customerService.getAllCustomers();

        // Assert
        assertEquals(0, actualCustomers.size());
    }

    @Test
    void getAllCustomers_shouldReturnListOfCustomers_whenCustomersExist() {
        // Arrange
        Customer customer1 = new Customer(1L, "John", "Doe", "john.doe@example.com");
        Customer customer2 = new Customer(2L, "Jane", "Smith", "jane.smith@example.com");
        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // Act
        List<Customer> actualCustomers = customerService.getAllCustomers();

        // Assert
        assertEquals(2, actualCustomers.size());
        assertEquals("John", actualCustomers.get(0).getFirstName());
        assertEquals("Smith", actualCustomers.get(1).getLastName());
    }

    // ... (more tests will go here)
}