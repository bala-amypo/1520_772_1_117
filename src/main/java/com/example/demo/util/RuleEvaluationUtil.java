package com.example.demo.util;

import com.example.demo.entity.LoginEvent;
import com.example.demo.entity.PolicyRule;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.ViolationRecordService; // <--- ADD THIS IMPORT
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleEvaluationUtil {

    private final PolicyRuleRepository policyRuleRepository;
    private final ViolationRecordService violationRecordService;

    public RuleEvaluationUtil(
            PolicyRuleRepository policyRuleRepository,
            ViolationRecordService violationRecordService
    ) {
        this.policyRuleRepository = policyRuleRepository;
        this.violationRecordService = violationRecordService;
    }

    public void evaluateLoginEvent(LoginEvent event) {
        if (event == null) {
            return;
        }

        List<PolicyRule> rules = policyRuleRepository.findAll();

        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId());
        record.setViolationType("LOGIN_VIOLATION");
        record.setDetails("Login policy violation");

        if (rules != null && !rules.isEmpty()) {
            PolicyRule rule = rules.get(0);
            record.setPolicyRuleId(rule.getId());
            record.setSeverity(rule.getSeverity());
        } else {
            record.setSeverity("LOW");
        }

        violationRecordService.logViolation(record);
    }
}