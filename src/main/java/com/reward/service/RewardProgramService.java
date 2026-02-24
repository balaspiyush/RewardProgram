package com.reward.service;

import com.reward.entity.CustomerDataEntity;
import com.reward.repository.CustomerRepository;
import com.reward.response.RewardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This service class is holding all the business logic to pull the data from DB
 * and also reward point calculation logic
 * @author Piyush Balas
 */
@Service
@RequiredArgsConstructor
public class RewardProgramService {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MMM");

    private final CustomerRepository customerRepository;

    /**
     * Service class to fetch all the customers from db and
     * calculate rewards
     */
    public List<RewardResponse> fetchRewards() {

        List<CustomerDataEntity> txnDataFromSource = customerRepository.findAllByOrderByCustomerId();

        return processTransactions(txnDataFromSource);
    }

    /**
     * Service class to fetch only given customer from DB and
     * calculate rewards
     */
    public List<RewardResponse> fetchRewardByCust(String customerId) {

        List<CustomerDataEntity> txnDataFromSource = customerRepository.findByCustomerId(customerId);

        return processTransactions(txnDataFromSource);
    }

    /**
     * Service class to fetch all the customers from db
     * and return as response
     */
    public List<CustomerDataEntity> fetchAllTxn() {
        return customerRepository.findAllByOrderByCustomerId();
    }

    private List<RewardResponse> processTransactions(List<CustomerDataEntity> txnDataFromSource) {

        Map<String, String> customerMap = new HashMap<>();
        Map<String, Map<String, Double>> customerByMonth = new HashMap<>();

        //Process all the transactions from source/db and calculate rewards
        for(CustomerDataEntity tx: txnDataFromSource) {

            String customerId = tx.getCustomerId();
            customerMap.put(customerId, tx.getCustomerName());

            String month = tx.getTransactionDate().format(dateFormat);
            Double rewardPoints = calculatePoints(tx.getAmount());

            //Checking if month already present in map and do the sum of rewards
            //else create new entry.
            Map<String, Double> monthMap = customerByMonth.computeIfAbsent(customerId, k -> new HashMap<>());
            Double existingPoints = monthMap.get(month);
            if(existingPoints==null) {
                monthMap.put(month, rewardPoints);
            } else {
                monthMap.put(month, existingPoints + rewardPoints);
            }
        }

        //Preparing response after all the calculation
        List<RewardResponse> responseList = new ArrayList<>();
        for(var entry : customerByMonth.entrySet()) {
            String customerId = entry.getKey();
            Map<String, Double> monthMap = entry.getValue();
            Double total = monthMap.values().stream().mapToDouble(Double::doubleValue).sum();
            responseList.add(new RewardResponse(customerId, customerMap.get(customerId),monthMap, total));
        }
        return responseList;
    }

    /**
     * This method will calculate reward points depending on txnAmount
     * @return rewardAmount
     */
    private double calculatePoints(Double txnAmount) {

        if(txnAmount == null) return 0;

        //if txnAmount is more than 100 than customer receive 2 points
        //per every dollar above 100
        double over100 = (txnAmount>100)?txnAmount-100:0;

        //If txnAmount between 50 and 100 than customer receive 1 points
        //per every dollar between 50 and 100
        double between50And100 = 0.0;
        if(txnAmount > 50 && txnAmount <= 100) {
            between50And100 = txnAmount - 50;
        } else if(txnAmount > 100){
            between50And100=50;
        }
        return (over100 * 2) + (between50And100);
    }
}
