package com.reward.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity class to map the data from DB to service
 * @author Piyush Balas
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
@RequiredArgsConstructor
public class CustomerDataEntity {

    @Id
    private String id;
    private String customerId;
    private String customerName;
    private LocalDate transactionDate;
    private double amount;
}
