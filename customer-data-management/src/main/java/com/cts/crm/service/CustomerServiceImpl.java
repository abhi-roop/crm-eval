package com.cts.crm.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cts.crm.exception.CustomerNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private final CustomerRepository customerRepository;

//	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		logger.info("Attempting to create customer: {}", customer);
		try {
			Customer savedCustomer = customerRepository.save(customer);
			logger.info("Customer created successfully: {}", savedCustomer);
			return savedCustomer;
		} catch (Exception e) {
			logger.error("Error creating customer: {}", e.getMessage(), e);
			throw e; // handled by Spring's exception handling
		}
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) {
		return customerRepository.findById(id).map(existingCustomer -> {
			customer.setCustomerId(id);
			return customerRepository.save(customer);
		}).orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
	}

	@Override
	public String deleteCustomer(Long id) {
		if (!customerRepository.existsById(id)) {
			throw new CustomerNotFoundException("Customer with ID " + id + " not found");
		}
		customerRepository.deleteById(id);
		return ("Customer Id Deleted");

	}

	@Override
	public List<Customer> getCustomersByRegion(String region) {
		return customerRepository.findByRegion(region);
	}

	@Override
	public List<Customer> getCustomersByInterest(String interest) {
		return customerRepository.findByInterestsContainingIgnoreCase(interest);
	}

	@Override
	public List<Customer> getCustomersByPurchasingHabit(String habit) {
		return customerRepository.findByPurchasingHabitsContainingIgnoreCase(habit);
	}

}