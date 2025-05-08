package com.cts.crm.service;

import com.cts.crm.model.Campaign;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CampaignService {
    List<Campaign> getAllCampaigns();
    Optional<Campaign> getCampaignById(Long id);
    Campaign createCampaign(Campaign campaign);
    Optional<Campaign> updateCampaign(Long id, Campaign campaign);
    void deleteCampaign(Long id);
    List<Campaign> searchCampaignsByName(String keyword);
    List<Campaign> getCampaignsStartingAfter(LocalDateTime startDate);
    List<Campaign> getCampaignsEndingBefore(LocalDateTime endDate);
    List<Campaign> getCampaignsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Campaign> getCampaignsEndingWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Campaign> getCampaignsStartingAfterAndEndingBefore(LocalDateTime startDate, LocalDateTime endDate);
}