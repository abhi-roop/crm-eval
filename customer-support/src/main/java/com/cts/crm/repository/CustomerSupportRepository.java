package com.cts.crm.repository;

import com.cts.crm.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerSupportRepository extends JpaRepository<Tickets, Long> {
	List<Tickets> findByCustomerId(Long customerId);

	List<Tickets> findByStatus(String status);
}