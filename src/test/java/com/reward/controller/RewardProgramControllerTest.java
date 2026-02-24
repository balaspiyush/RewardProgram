package com.reward.controller;

import com.reward.entity.CustomerDataEntity;
import com.reward.response.RewardResponse;
import com.reward.service.RewardProgramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardProgramController.class)
class RewardProgramControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RewardProgramService rewardProgramService;

    private static final String BASE_URL="/v1/api";

    @Test
    void getRewardsForAll_shouldReturnRewardsList() throws Exception {
        RewardResponse r1 = new RewardResponse("CUST005","CustomerFive", Map.of("2025-01",50.00),0.0);
        RewardResponse r2 = new RewardResponse("CUST006","CustomerSix", Map.of("2025-01",120.00),90.0);

        when(rewardProgramService.fetchRewards()).thenReturn(List.of(r1,r2));

        mockMvc.perform(get(BASE_URL+"/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].customerId").value("CUST005"))
                .andExpect(jsonPath("$[0].customerName").value("CustomerFive"))
                .andExpect(jsonPath("$[0].totalRewards").value(0.0))
                .andExpect(jsonPath("$[1].customerId").value("CUST006"))
                .andExpect(jsonPath("$[1].customerName").value("CustomerSix"))
                .andExpect(jsonPath("$[1].totalRewards").value(90.0));
    }

    @Test
    void getRewardsForAll_shouldReturnAllTransactions() throws Exception {
        CustomerDataEntity customerDataEntity = new CustomerDataEntity();
        customerDataEntity.setCustomerId("C001");
        customerDataEntity.setCustomerName("CustOne");
        customerDataEntity.setAmount(150.0);

        CustomerDataEntity customerDataEntity2 = new CustomerDataEntity();
        customerDataEntity2.setCustomerId("C001");
        customerDataEntity2.setCustomerName("CustOne");
        customerDataEntity2.setAmount(45.0);

        when(rewardProgramService.fetchAllTxn()).thenReturn(List.of(customerDataEntity,customerDataEntity2));

        mockMvc.perform(get(BASE_URL+"/details")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].customerId").value("C001"))
                .andExpect(jsonPath("$[0].amount").value(150.0))
                .andExpect(jsonPath("$[1].amount").value(45.0));
    }

    @Test
    void getRewardsForCustomer_shouldReturnRewards() throws Exception {
        String customerId="CUST005";
        RewardResponse r1 = new RewardResponse("CUST005","CustomerFive", Map.of("2025-01",50.00),0.0);

        when(rewardProgramService.fetchRewardByCust(anyString())).thenReturn(List.of(r1));
        mockMvc.perform(get(BASE_URL+"/"+customerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerId").value("CUST005"))
                .andExpect(jsonPath("$[0].customerName").value("CustomerFive"))
                .andExpect(jsonPath("$[0].totalRewards").value(0.0));
    }
}
