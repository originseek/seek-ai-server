package com.sa.com.entity;

import com.sa.com.enums.PolicyStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Policy {
    private String policyNo;
    private String name;
    private String idNo;
    private String plateNo;
    private String carBand;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private PolicyStatusEnum policyStatus;
}
