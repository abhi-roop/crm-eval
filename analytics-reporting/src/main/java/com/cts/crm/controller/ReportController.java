package com.cts.crm.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.crm.dto.CustomerData;
import com.cts.crm.dto.CustomerTicketResponse;
import com.cts.crm.dto.MarketingAutomation;
import com.cts.crm.dto.SalesAutomation;
import com.cts.crm.model.Report;
import com.cts.crm.service.ReportService;

@RestController // Marks this class as a REST controller, allowing it to handle HTTP requests
@RequestMapping("/api/analytics/reports") // Defines the base URL for all endpoints in this controller
public class ReportController {

    private final ReportService reportService;

    // Constructor injection to ensure ReportService dependency is provided
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Retrieves all customer ticket reports
    @GetMapping
    public CustomerTicketResponse getAllReports() {
        return reportService.getAllReports();
    }

    // Retrieves all customer data
    @GetMapping("/getAllCustomer")
    public List<CustomerData> getAllCustomers() {
        return reportService.getAllCustomers();
    }

    // Retrieves all marketing automation campaigns
    @GetMapping("/getAllCampaigns")
    public List<MarketingAutomation> getAllCampaigns() {
        return reportService.getAllCampaigns();
    }

    // Retrieves all sales automation data
    @GetMapping("/getAllSales")
    public List<SalesAutomation> getAllSales() {
        return reportService.getAllSales();
    }

    // Retrieves reports generated between two specified dates
    @GetMapping("/generated-between")
    public List<Report> getReportsGeneratedBetween(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
        LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
        return reportService.getReportsGeneratedBetween(parsedStartDate, parsedEndDate);
    }

    // Retrieves reports generated before a specified date
    @GetMapping("/generated-before")
    public List<Report> getReportsGeneratedBefore(@RequestParam String date) {
        LocalDateTime parsedDate = LocalDateTime.parse(date);
        return reportService.getReportsGeneratedBefore(parsedDate);
    }

    // Retrieves reports generated after a specified date
    @GetMapping("/generated-after")
    public List<Report> getReportsGeneratedAfter(@RequestParam String date) {
        LocalDateTime parsedDate = LocalDateTime.parse(date);
        return reportService.getReportsGeneratedAfter(parsedDate);
    }
}
