package com.reward.repository;

import com.reward.entity.CustomerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository class to pull data from database
 * @author Piyush Balas
 */
public interface CustomerRepository extends JpaRepository<CustomerDataEntity, Long> {
    List<CustomerDataEntity> findAllByOrderByCustomerId();
    List<CustomerDataEntity> findByCustomerId(String customerId);
}
