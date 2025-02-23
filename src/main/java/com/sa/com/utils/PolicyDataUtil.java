package com.sa.com.utils;

import com.sa.com.entity.Policy;
import com.sa.com.enums.PolicyStatusEnum;
import com.sa.com.vo.PolicyData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PolicyDataUtil {
    /**
     * 初始化数据
     */
    public static PolicyData initPolicyData() {
        PolicyData policyData = new PolicyData();
        List<String> names = List.of("王虎", "张三", "李四", "李明", "邹庄");
        List<String> plateNos = List.of("京A9A11L", "京C9A11L", "京H9A11L", "京F9A11L", "京K9A11L");
        List<String> carBands = List.of("奔驰E", "宝马X5", "奥迪A8", "小米su7", "坦克300");
        List<Policy> policyList = new ArrayList<Policy>();
        for (int i = 0; i < 5; i++) {
            String name = names.get(i);
            String plateNo = plateNos.get(i);
            String carBand = carBands.get(i);
            LocalDate date = LocalDate.now().plusDays(2 * (i + 1));
            Policy policy = new Policy("1000000" + (i + 1), name, "110****3" + (i + 1), plateNo, carBand, new BigDecimal(5000), date, date, PolicyStatusEnum.CONFIRMED);
            policyList.add(policy);
        }
        policyData.setPolicylist(policyList);
        return policyData;
    }
}
