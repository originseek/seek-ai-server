package com.sa.com.service.impl;

import com.sa.com.entity.Policy;
import com.sa.com.enums.PolicyStatusEnum;
import com.sa.com.service.PolicyService;
import com.sa.com.utils.PolicyDataUtil;
import com.sa.com.vo.PolicyData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {
    private final PolicyData db;

    public PolicyServiceImpl() {
        db = PolicyDataUtil.initPolicyData();
    }

    @Override
    public List<Policy> getPolicyList() {
        return db.getPolicylist();
    }

    @Override
    public Policy findPolicy(String policyNo, String name) {
        return db.getPolicylist().stream()
                .filter(t -> t.getPolicyNo().equalsIgnoreCase(policyNo))
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Policy not find"));
    }

    @Override
    public void cancelPolicy(String policyNo, String name, String newDate) {
        Policy policy = findPolicy(policyNo, name);
        policy.setPolicyStatus(PolicyStatusEnum.CANCELLED);
    }
}
