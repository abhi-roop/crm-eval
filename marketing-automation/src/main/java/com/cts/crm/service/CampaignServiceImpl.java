package com.cts.crm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.crm.model.Campaign;
import com.cts.crm.repository.MarketingAutomationRepository;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final MarketingAutomationRepository campaignRepository;

  //  @Autowired
    public CampaignServiceImpl(MarketingAutomationRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Optional<Campaign> updateCampaign(Long id, Campaign campaign) {
        return campaignRepository.findById(id)
                .map(existingCampaign -> {
                    existingCampaign.setName(campaign.getName());
                    existingCampaign.setStartDate(campaign.getStartDate());
                    existingCampaign.setEndDate(campaign.getEndDate());
                    return campaignRepository.save(existingCampaign);
                });
    }

    @Override
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    @Override
    public List<Campaign> searchCampaignsByName(String keyword) {
        return campaignRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Campaign> getCampaignsStartingAfter(LocalDateTime startDate) {
        return campaignRepository.findByStartDateAfter(startDate);
    }

    @Override
    public List<Campaign> getCampaignsEndingBefore(LocalDateTime endDate) {
        return campaignRepository.findByEndDateBefore(endDate);
    }

    @Override
    public List<Campaign> getCampaignsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return campaignRepository.findByStartDateBetween(startDate, endDate);
    }

    @Override
    public List<Campaign> getCampaignsEndingWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return campaignRepository.findByEndDateBetween(startDate, endDate);
    }

    @Override
    public List<Campaign> getCampaignsStartingAfterAndEndingBefore(LocalDateTime startDate, LocalDateTime endDate) {
        return campaignRepository.findByStartDateAfterAndEndDateBefore(startDate, endDate);
    }
}
