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

@RestController
@RequestMapping("/api/analytics/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public CustomerTicketResponse getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/getAllCustomer")
    public List<CustomerData> getAllCustomers() {
        return reportService.getAllCustomers();
    }

    @GetMapping("/getAllCampaigns")
    public List<MarketingAutomation> getAllCampaigns() {
        return reportService.getAllCampaigns();
    }

    @GetMapping("/getAllSales")
    public List<SalesAutomation> getAllSales() {
        return reportService.getAllSales();
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