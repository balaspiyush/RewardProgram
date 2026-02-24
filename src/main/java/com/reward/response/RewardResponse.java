package com.reward.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Response class with all the response fields
 */
@Setter
@Getter
@AllArgsConstructor
public class RewardResponse {
    String customerId;
    String customerName;
    Map<String, Double> rewardsPerMonth;
    Double totalRewards;
}
