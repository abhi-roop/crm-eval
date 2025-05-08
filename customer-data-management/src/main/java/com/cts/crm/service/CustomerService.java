package com.cts.crm.service;

import com.cts.crm.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    void logInteraction(Long customerId, String interactionDetails); // Placeholder for interaction logging
    List<Customer> getCustomersByRegion(String region);
    List<Customer> getCustomersByInterest(String interest);
    List<Customer> getCustomersByPurchasingHabit(String habit);
}