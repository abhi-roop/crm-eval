package com.cts.crm.service;

import com.cts.crm.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    String deleteCustomer(Long id); // Changed return type to String
}