package com.cts.crm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.crm.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByGeneratedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Report> findByGeneratedDateBefore(LocalDateTime date);

    List<Report> findByGeneratedDateAfter(LocalDateTime date);

    // Find reports generated on a specific date
    List<Report> findByGeneratedDate(LocalDateTime date);

    // Find latest report (sorted by generated date)
    Report findTopByOrderByGeneratedDateDesc();
}
