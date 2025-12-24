package com.example.demo.util;

import java.time.Instant;
import java.util.List;

import com.example.demo.entity.LoginEvent;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.repository.ViolationRecordRepository;
import org.springframework.stereotype.Component;
import com.example.demo.entity.PolicyRule;

@Component
public class RuleEvaluationUtil {

    private final PolicyRuleRepository policyRuleRepository;
    private final ViolationRecordRepository violationRecordRepository;

    public RuleEvaluationUtil(
            PolicyRuleRepository policyRuleRepository,
            ViolationRecordRepository violationRecordRepository
    ) {
        this.policyRuleRepository = policyRuleRepository;
        this.violationRecordRepository = violationRecordRepository;
    }

    public void evaluateLoginEvent(LoginEvent event) {

        if (event.getIpAddress() == null || event.getDeviceId() == null) {
            throw new IllegalArgumentException("IP address and deviceId required");
        }

        List<PolicyRule> rules = policyRuleRepository.findAll();
        if (rules.isEmpty()) {
            return;
        }

        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId());
        record.setViolationType("LOGIN_VIOLATION");
        record.setRuleName(rules.get(0).getName());
        record.setTimestamp(Instant.now());

        violationRecordRepository.save(record);
    }
}
