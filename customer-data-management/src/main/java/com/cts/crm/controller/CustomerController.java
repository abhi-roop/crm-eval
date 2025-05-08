package com.cts.crm.controller;

import com.cts.crm.exception.CustomerNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
	}

	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
		updatedCustomer.setCustomerId(id); // Ensure ID is set
		return customerService.updateCustomer(id, updatedCustomer);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}




	@GetMapping("/region/{region}")
	public List<Customer> getCustomersByRegion(@PathVariable String region) {
		return customerService.getCustomersByRegion(region);
	}

	@GetMapping("/interest")
	public List<Customer> getCustomersByInterest(@RequestParam String interest) {
		return customerService.getCustomersByInterest(interest);
	}

	@GetMapping("/habit")
	public List<Customer> getCustomersByPurchasingHabit(@RequestParam String habit) {
		return customerService.getCustomersByPurchasingHabit(habit);
	}
}