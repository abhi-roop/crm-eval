package com.cts.crm.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cts.crm.exception.CustomerNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCustomers_shouldReturnListOfCustomers() {
        // Arrange
        Customer customer1 = new Customer(1L, "John Doe", "john.doe@example.com", null, null, null, null, null, null, null);
        Customer customer2 = new Customer(2L, "Jane Smith", "jane.smith@example.com", null, null, null, null, null, null, null);
        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // Act
        List<Customer> actualCustomers = customerService.getAllCustomers();

        // Assert
        assertEquals(2, actualCustomers.size());
        assertEquals("John Doe", actualCustomers.get(0).getName());
        assertEquals("Jane Smith", actualCustomers.get(1).getName());
    }

    @Test
    void getCustomerById_shouldReturnCustomer_whenExists() {
        // Arrange
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com", null, null, null, null, null, null, null);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> actualCustomer = customerService.getCustomerById(1L);

        // Assert
        assertTrue(actualCustomer.isPresent());
        assertEquals("John Doe", actualCustomer.get().getName());
    }

    @Test
    void getCustomerById_shouldReturnEmptyOptional_whenNotFound() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Customer> actualCustomer = customerService.getCustomerById(1L);

        // Assert
        assertFalse(actualCustomer.isPresent());
    }

    @Test
    void createCustomer_shouldSaveAndReturnCustomer() {
        // Arrange
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com", null, null, null, null, null, null, null);
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer savedCustomer = customerService.createCustomer(customer);

        // Assert
        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
    }

    @Test
    void updateCustomer_shouldUpdateExistingCustomer() {
        // Arrange
        Customer existingCustomer = new Customer(1L, "John Doe", "john.doe@example.com", null, null, null, null, null, null, null);
        Customer updatedCustomer = new Customer(1L, "Johnny Doe", "johnny.doe@example.com", null, null, null, null, null, null, null);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

        // Act
        Customer result = customerService.updateCustomer(1L, updatedCustomer);

        // Assert
        assertNotNull(result);
        assertEquals("Johnny Doe", result.getName());
    }

    @Test
    void updateCustomer_shouldThrowException_whenCustomerNotFound() {
        // Arrange
        Customer updatedCustomer = new Customer(1L, "Johnny Doe", "johnny.doe@example.com", null, null, null, null, null, null, null);
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(1L, updatedCustomer));
    }

    @Test
    void deleteCustomer_shouldDelete_whenCustomerExists() {
        // Arrange
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        // Act
        customerService.deleteCustomer(1L);

        // Assert
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCustomer_shouldThrowException_whenCustomerNotFound() {
        // Arrange
        when(customerRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(1L));
    }
}
