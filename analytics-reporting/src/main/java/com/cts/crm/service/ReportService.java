package com.cts.crm.service;

import com.cts.crm.model.Report;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReportService {
    List<Report> getAllReports();
    Optional<Report> getReportById(Long id);
    Report createReport(String reportType, LocalDateTime generatedDate, Map<String, Object> datapoints);
    Optional<Report> updateReport(Long id, String reportType, LocalDateTime generatedDate, Map<String, Object> datapoints);
    void deleteReport(Long id);
    List<Report> getReportsByType(String reportType);
    List<Report> getReportsGeneratedBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Report> getReportsGeneratedBefore(LocalDateTime date);
    List<Report> getReportsGeneratedAfter(LocalDateTime date);
}