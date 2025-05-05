package com.cts.crm.repository;

import com.cts.crm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can add custom query methods here if needed
    // For example: List<Customer> findByLastName(String lastName);
}