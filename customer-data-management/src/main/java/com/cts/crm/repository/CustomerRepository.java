package com.cts.crm.repository;

import com.cts.crm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByRegion(String region);
    List<Customer> findByInterestsContainingIgnoreCase(String interest);
    List<Customer> findByPurchasingHabitsContainingIgnoreCase(String habit);
}