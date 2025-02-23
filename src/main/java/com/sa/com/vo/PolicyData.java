package com.sa.com.vo;

import com.sa.com.entity.Policy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PolicyData {
    private List<Policy> policylist = new ArrayList<>();
}
