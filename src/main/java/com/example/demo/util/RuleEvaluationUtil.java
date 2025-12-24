package com.example.demo.util;

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

        List<PolicyRule> rules = policyRuleRepository.findAll();

        if (rules == null || rules.isEmpty()) {
            return;
        }

        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId());
        record.setViolationType("LOGIN_VIOLATION");

        violationRecordRepository.save(record);
    }
}
