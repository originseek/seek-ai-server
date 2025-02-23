package com.sa.com.config;

import com.sa.com.entity.Policy;
import com.sa.com.service.PolicyService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.time.LocalDate;
import java.util.function.Function;

@Configuration
public class PolicyOperateDescription {
    @Resource
    private PolicyService policyService;

    public record PolicyRequest(String policyNo, String name) {
    }

    public record CancelPolicyRequest(String policyNo, String name) {
    }

    @Bean
    @Description("保单详情")
    public Function<PolicyRequest, Policy> findPolicy() {
        return request -> policyService.findPolicy(request.policyNo(), request.name());
    }

    @Bean
    @Description("退保")
    public Function<CancelPolicyRequest, String> cancelPolicy() {
        return request -> {
            LocalDate date = LocalDate.now();
            policyService.cancelPolicy(request.policyNo(), request.name(), date.toString());
            return "";
        };
    }
}
