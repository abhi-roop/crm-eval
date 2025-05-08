package com.cts.crm.controller;

import com.cts.crm.model.Campaign;
import com.cts.crm.service.CampaignService; // Assuming CampaignService is in com.cts.crm.service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/marketing/campaigns")
public class MarketingAutomationController {

    private final CampaignService campaignService;

    @Autowired
    public MarketingAutomationController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return campaignService.getCampaignById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign with ID " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/{id}")
    public Campaign updateCampaign(@PathVariable Long id, @RequestBody Campaign updatedCampaign) {
        return campaignService.updateCampaign(id, updatedCampaign)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign with ID " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
    }

    @GetMapping("/search")
    public List<Campaign> searchCampaignsByName(@RequestParam String name) {
        return campaignService.searchCampaignsByName(name);
    }

    @GetMapping("/start-after")
    public List<Campaign> getCampaignsStartingAfter(@RequestParam String startDate) {
        try {
            LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
            return campaignService.getCampaignsStartingAfter(parsedStartDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for startDate");
        }
    }

    @GetMapping("/end-before")
    public List<Campaign> getCampaignsEndingBefore(@RequestParam String endDate) {
        try {
            LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
            return campaignService.getCampaignsEndingBefore(parsedEndDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for endDate");
        }
    }

    @GetMapping("/within-dates")
    public List<Campaign> getCampaignsWithinDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
            LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
            return campaignService.getCampaignsWithinDateRange(parsedStartDate, parsedEndDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for startDate or endDate");
        }
    }

    @GetMapping("/end-within")
    public List<Campaign> getCampaignsEndingWithinDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
            LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
            return campaignService.getCampaignsEndingWithinDateRange(parsedStartDate, parsedEndDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for startDate or endDate");
        }
    }

    @GetMapping("/start-after-end-before")
    public List<Campaign> getCampaignsStartingAfterAndEndingBefore(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
            LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
            return campaignService.getCampaignsStartingAfterAndEndingBefore(parsedStartDate, parsedEndDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for startDate or endDate");
        }
    }
}