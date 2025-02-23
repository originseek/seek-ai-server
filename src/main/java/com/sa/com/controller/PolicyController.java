package com.sa.com.controller;

import com.sa.com.entity.Policy;
import com.sa.com.service.PolicyService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PolicyController {
    @Resource
    private PolicyService policyService;

    @CrossOrigin
    @GetMapping(value = "/policy/list")
    public List<Policy> getPolicyList() {
        return policyService.getPolicyList();
    }
}
