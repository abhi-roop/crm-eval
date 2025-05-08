package com.cts.crm.repository;

import com.cts.crm.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByReportType(String reportType);
    List<Report> findByGeneratedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Report> findByGeneratedDateBefore(LocalDateTime date);
    List<Report> findByGeneratedDateAfter(LocalDateTime date);
}