package com.cts.crm.controller;

import com.cts.crm.model.Report;
import com.cts.crm.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics/reports")
public class ReportController {

	private final ReportService reportService;
	private final ObjectMapper objectMapper;

	@Autowired
	public ReportController(ReportService reportService, ObjectMapper objectMapper) {
		this.reportService = reportService;
		this.objectMapper = objectMapper;
	}

	@GetMapping
	public List<Report> getAllReports() {
		return reportService.getAllReports();
	}

	@GetMapping("/type/{reportType}")
	public List<Report> getReportsByType(@PathVariable String reportType) {
		return reportService.getReportsByType(reportType);
	}

	@GetMapping("/generated-between")
	public List<Report> getReportsGeneratedBetween(@RequestParam String startDate, @RequestParam String endDate) {
		LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
		LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
		return reportService.getReportsGeneratedBetween(parsedStartDate, parsedEndDate);
	}

	@GetMapping("/generated-before")
	public List<Report> getReportsGeneratedBefore(@RequestParam String date) {
		LocalDateTime parsedDate = LocalDateTime.parse(date);
		return reportService.getReportsGeneratedBefore(parsedDate);
	}

	@GetMapping("/generated-after")
	public List<Report> getReportsGeneratedAfter(@RequestParam String date) {
		LocalDateTime parsedDate = LocalDateTime.parse(date);
		return reportService.getReportsGeneratedAfter(parsedDate);
	}

}