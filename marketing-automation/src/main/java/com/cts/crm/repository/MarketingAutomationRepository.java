package com.cts.crm.repository;

import com.cts.crm.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MarketingAutomationRepository extends JpaRepository<Campaign, Long> {

    // Custom query methods can be added here

    List<Campaign> findByNameContainingIgnoreCase(String keyword);

    List<Campaign> findByStartDateAfter(LocalDateTime startDate);

    List<Campaign> findByEndDateBefore(LocalDateTime endDate);

    List<Campaign> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Campaign> findByEndDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Example of combining criteria
    List<Campaign> findByStartDateAfterAndEndDateBefore(LocalDateTime startDate, LocalDateTime endDate);
}