package com.cts.crm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.crm.dto.CustomerAnalytics;
import com.cts.crm.dto.CustomerData;
import com.cts.crm.dto.CustomerSupport;
import com.cts.crm.dto.CustomerTicketResponse;
import com.cts.crm.dto.MarketingAutomation;
import com.cts.crm.dto.SalesAutomation;
import com.cts.crm.exception.ReportNotFoundException;
import com.cts.crm.feignclient.CustomerClient;
import com.cts.crm.feignclient.MarketingClient;
import com.cts.crm.feignclient.SalesClient;
import com.cts.crm.feignclient.SupportClient;
import com.cts.crm.model.Report;
import com.cts.crm.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;
	private final CustomerClient customerClient;
	private final SupportClient supportClient;
	private final SalesClient salesClient;
	private final MarketingClient marketingClient;

	public ReportServiceImpl(ReportRepository reportRepository, CustomerClient customerClient,
			SupportClient supportClient, SalesClient salesClient, MarketingClient marketingClient) {
		this.reportRepository = reportRepository;
		this.customerClient = customerClient;
		this.supportClient = supportClient;
		this.salesClient = salesClient;
		this.marketingClient = marketingClient;
	}

	@Override
	public CustomerTicketResponse getAllReports() {
		List<CustomerData> customers = customerClient.getAllCustomers();
		List<CustomerAnalytics> customerAnalyticsList = new ArrayList<>();

		for (CustomerData customer : customers) {
			List<CustomerSupport> tickets = supportClient.getTicketsByCustomerId(customer.getCustomerId());
			customerAnalyticsList.add(new CustomerAnalytics(customer, tickets));
		}

		List<SalesAutomation> salesAnalyticsList = salesClient.getAllSales();
		List<MarketingAutomation> marketingAnalyticsList = marketingClient.getAllCampaigns();

		return new CustomerTicketResponse(customerAnalyticsList, salesAnalyticsList, marketingAnalyticsList);
	}

	@Override
	public Optional<Report> getReportById(Long id) {
		return reportRepository.findById(id);
	}

	@Override
	public void deleteReport(Long id) {
		if (!reportRepository.existsById(id)) {
			throw new ReportNotFoundException("Report with ID " + id + " not found");
		}
		reportRepository.deleteById(id);
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

	@Override
	public List<CustomerData> getAllCustomers() {
		return customerClient.getAllCustomers();
	}

	@Override
	public List<MarketingAutomation> getAllCampaigns() {
		return marketingClient.getAllCampaigns();
	}

	@Override
	public List<SalesAutomation> getAllSales() {
		return salesClient.getAllSales();
	}

	@Override
	public Report createReport(String reportType, LocalDateTime generatedDate, Map<String, Object> datapoints) {
		return null; // Placeholder implementation
	}
}