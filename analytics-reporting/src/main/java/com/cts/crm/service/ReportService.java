package com.cts.crm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cts.crm.dto.CustomerData;
import com.cts.crm.dto.CustomerTicketResponse;
import com.cts.crm.dto.MarketingAutomation;
import com.cts.crm.dto.SalesAutomation;
import com.cts.crm.model.Report;

public interface ReportService {

	CustomerTicketResponse getAllReports();

	Optional<Report> getReportById(Long id);

	void deleteReport(Long id);

	List<Report> getReportsGeneratedBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<Report> getReportsGeneratedBefore(LocalDateTime date);

	List<Report> getReportsGeneratedAfter(LocalDateTime date);

	List<CustomerData> getAllCustomers();

	List<MarketingAutomation> getAllCampaigns();

	List<SalesAutomation> getAllSales();

	Report createReport(String reportType, LocalDateTime generatedDate, Map<String, Object> datapoints);
}