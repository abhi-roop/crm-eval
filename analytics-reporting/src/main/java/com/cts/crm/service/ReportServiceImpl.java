package com.cts.crm.service;

import com.cts.crm.model.Report;
import com.cts.crm.repository.ReportRepository;
import com.cts.crm.exception.ReportNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ObjectMapper objectMapper) {
        this.reportRepository = reportRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public Report createReport(String reportType, LocalDateTime generatedDate, Map<String, Object> datapoints) {
        Report report = new Report();
        report.setReportType(reportType);
        report.setGeneratedDate(generatedDate);
        try {
            report.setDatapoints(objectMapper.writeValueAsString(datapoints));
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            // Handle JSON serialization error appropriately
            throw new RuntimeException("Error serializing datapoints to JSON", e);
        }
        return reportRepository.save(report);
    }

    @Override
    public Optional<Report> updateReport(Long id, String reportType, LocalDateTime generatedDate, Map<String, Object> datapoints) {
        return Optional.ofNullable(reportRepository.findById(id)
                .map(existingReport -> {
                    existingReport.setReportType(reportType);
                    existingReport.setGeneratedDate(generatedDate);
                    try {
                        existingReport.setDatapoints(objectMapper.writeValueAsString(datapoints));
                    } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                        // Handle JSON serialization error appropriately
                        throw new RuntimeException("Error serializing datapoints to JSON", e);
                    }
                    return reportRepository.save(existingReport);
                })
                .orElseThrow(() -> new ReportNotFoundException("Report with ID " + id + " not found")));
    }

    @Override
    public void deleteReport(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new ReportNotFoundException("Report with ID " + id + " not found");
        }
        reportRepository.deleteById(id);
    }

    @Override
    public List<Report> getReportsByType(String reportType) {
        return reportRepository.findByReportType(reportType);
    }

    @Override
    public List<Report> getReportsGeneratedBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return reportRepository.findByGeneratedDateBetween(startDate, endDate);
    }

    @Override
    public List<Report> getReportsGeneratedBefore(LocalDateTime date) {
        return reportRepository.findByGeneratedDateBefore(date);
    }

    @Override
    public List<Report> getReportsGeneratedAfter(LocalDateTime date) {
        return reportRepository.findByGeneratedDateAfter(date);
    }
}