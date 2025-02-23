package com.sa.com.service;

import com.sa.com.entity.Policy;

import java.util.List;

public interface PolicyService {
    /**
     * 获取保单详情列表
     */
    List<Policy> getPolicyList();

    /**
     * 查找保单
     */
    Policy findPolicy(String policyNo, String name);

    /**
     * 取消保单
     */
    void cancelPolicy(String policyNo, String name, String newDate);
}
