package com.cts.crm.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.crm.exception.CustomerNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

   // @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
    }

    // Create a new customer with validation
    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Update customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedCustomer) {
        updatedCustomer.setCustomerId(id); // Ensure ID consistency
        return customerService.updateCustomer(id, updatedCustomer);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
         return customerService.deleteCustomer(id);
    }

    // Get customers by region
    @GetMapping("/region/{region}")
    public List<Customer> getCustomersByRegion(@PathVariable String region) {
        return customerService.getCustomersByRegion(region);
    }

    // Get customers by interest
    @GetMapping("/interest")
    public List<Customer> getCustomersByInterest(@RequestParam String interest) {
        return customerService.getCustomersByInterest(interest);
    }

    // Get customers by purchasing habits
    @GetMapping("/habit")
    public List<Customer> getCustomersByPurchasingHabit(@RequestParam String habit) {
        return customerService.getCustomersByPurchasingHabit(habit);
    }
}
