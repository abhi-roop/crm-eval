package com.cts.crm.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.crm.exception.CampaignNotFoundException;
import com.cts.crm.exception.InvalidDateFormatException;
import com.cts.crm.model.Campaign;
import com.cts.crm.service.CampaignService;

@RestController
@RequestMapping("/api/marketing/campaigns")
public class MarketingAutomationController {

    private final CampaignService campaignService;

   // @Autowired
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
                .orElseThrow(() -> new CampaignNotFoundException("Campaign with ID " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/{id}")
    public Campaign updateCampaign(@PathVariable Long id, @RequestBody Campaign updatedCampaign) {
        return campaignService.updateCampaign(id, updatedCampaign)
                .orElseThrow(() -> new CampaignNotFoundException("Campaign with ID " + id + " not found"));
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
            throw new InvalidDateFormatException("Invalid date format for startDate");
        }
    }

    @GetMapping("/end-before")
    public List<Campaign> getCampaignsEndingBefore(@RequestParam String endDate) {
        try {
            LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
            return campaignService.getCampaignsEndingBefore(parsedEndDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format for endDate");
        }
    }

    @GetMapping("/within-dates")
    public List<Campaign> getCampaignsWithinDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
            LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
            return campaignService.getCampaignsWithinDateRange(parsedStartDate, parsedEndDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format for startDate or endDate");
        }
    }
}
