package com.reward.controller;

import com.reward.entity.CustomerDataEntity;
import com.reward.response.RewardResponse;
import com.reward.service.RewardProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller class with 3 endpoints to return
 * 1. customer wise rewards
 * 2. all customers rewards
 * 3. all transactions present in DB
 * @author Piyush Balas
 */
@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
public class RewardProgramController {
    private final RewardProgramService rewardProgramService;

    /**
     * This method is used to return rewards for all the customers
     * @return List of all the customers with rewards
     */
    @GetMapping
    public List<RewardResponse> getRewardsForAll() {
        return rewardProgramService.fetchRewards();
    }

    /**
     * This method will return all the txns from source
     */
    @GetMapping("/details")
    public List<CustomerDataEntity> getAllTransactions() {
        return rewardProgramService.fetchAllTxn();
    }

    /**
     * This method will return all the txns from source
     */
    @GetMapping("/{customerId}")
    public List<RewardResponse> getRewardsByCustomer(@PathVariable String customerId) {
        return rewardProgramService.fetchRewardByCust(customerId);
    }

}
