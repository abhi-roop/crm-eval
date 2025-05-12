package com.cts.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.crm.model.Campaign;
import com.cts.crm.repository.MarketingAutomationRepository;

@ExtendWith(MockitoExtension.class)
class CampaignServiceImplTest {

    @Mock
    private MarketingAutomationRepository campaignRepository;

    @InjectMocks
    private CampaignServiceImpl campaignService;

    private Campaign campaign;

    @BeforeEach
    void setUp() {
        campaign = new Campaign();
        campaign.setCampaignId(1L);
        campaign.setName("New Product Launch");
        campaign.setStartDate(LocalDateTime.now().plusDays(1));
        campaign.setEndDate(LocalDateTime.now().plusWeeks(1));
    }

    @Test
    void testGetAllCampaigns() {
        when(campaignRepository.findAll()).thenReturn(Arrays.asList(campaign));

        List<Campaign> campaigns = campaignService.getAllCampaigns();
        assertFalse(campaigns.isEmpty());
        assertEquals(1, campaigns.size());
        assertEquals("New Product Launch", campaigns.get(0).getName());

        verify(campaignRepository, times(1)).findAll();
    }

    @Test
    void testGetCampaignById() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        Optional<Campaign> retrievedCampaign = campaignService.getCampaignById(1L);
        assertTrue(retrievedCampaign.isPresent());
        assertEquals("New Product Launch", retrievedCampaign.get().getName());

        verify(campaignRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateCampaign() {
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign createdCampaign = campaignService.createCampaign(campaign);
        assertNotNull(createdCampaign);
        assertEquals("New Product Launch", createdCampaign.getName());

        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test
    void testUpdateCampaign() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Optional<Campaign> updatedCampaign = campaignService.updateCampaign(1L, campaign);
        assertTrue(updatedCampaign.isPresent());
        assertEquals("New Product Launch", updatedCampaign.get().getName());

        verify(campaignRepository, times(1)).findById(1L);
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test
    void testDeleteCampaign() {
        doNothing().when(campaignRepository).deleteById(1L);

        assertDoesNotThrow(() -> campaignService.deleteCampaign(1L));

        verify(campaignRepository, times(1)).deleteById(1L);
    }

    @Test
    void testSearchCampaignsByName() {
        when(campaignRepository.findByNameContainingIgnoreCase("Launch")).thenReturn(Arrays.asList(campaign));

        List<Campaign> campaigns = campaignService.searchCampaignsByName("Launch");
        assertFalse(campaigns.isEmpty());

        verify(campaignRepository, times(1)).findByNameContainingIgnoreCase("Launch");
    }

    @Test
    void testGetCampaignsStartingAfter() {
        when(campaignRepository.findByStartDateAfter(any(LocalDateTime.class))).thenReturn(Arrays.asList(campaign));

        List<Campaign> campaigns = campaignService.getCampaignsStartingAfter(LocalDateTime.now());
        assertFalse(campaigns.isEmpty());

        verify(campaignRepository, times(1)).findByStartDateAfter(any(LocalDateTime.class));
    }

    @Test
    void testGetCampaignsEndingBefore() {
        when(campaignRepository.findByEndDateBefore(any(LocalDateTime.class))).thenReturn(Arrays.asList(campaign));

        List<Campaign> campaigns = campaignService.getCampaignsEndingBefore(LocalDateTime.now());
        assertFalse(campaigns.isEmpty());

        verify(campaignRepository, times(1)).findByEndDateBefore(any(LocalDateTime.class));
    }

    @Test
    void testGetCampaignsWithinDateRange() {
        when(campaignRepository.findByStartDateBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
            .thenReturn(Arrays.asList(campaign));

        List<Campaign> campaigns = campaignService.getCampaignsWithinDateRange(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        assertFalse(campaigns.isEmpty());

        verify(campaignRepository, times(1)).findByStartDateBetween(any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void testGetCampaignsStartingAfterAndEndingBefore() {
        when(campaignRepository.findByStartDateAfterAndEndDateBefore(any(LocalDateTime.class), any(LocalDateTime.class)))
            .thenReturn(Arrays.asList(campaign));

        List<Campaign> campaigns = campaignService.getCampaignsStartingAfterAndEndingBefore(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        assertFalse(campaigns.isEmpty());

        verify(campaignRepository, times(1)).findByStartDateAfterAndEndDateBefore(any(LocalDateTime.class), any(LocalDateTime.class));
    }
}
