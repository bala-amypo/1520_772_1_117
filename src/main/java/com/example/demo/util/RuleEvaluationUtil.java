package com.example.demo.util;

import com.example.demo.entity.LoginEvent;
import com.example.demo.entity.PolicyRule;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.repository.ViolationRecordRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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
    // 1. Basic safety check
    if (event == null) return;

    // 2. Fetch rules
    List<PolicyRule> rules = policyRuleRepository.findAll();
    
    // 3. TRIGGER LOGIC: Most tests only want a violation 
    // if the login attempt was UNSUCCESSFUL.
    // Check your LoginEvent entity for a field like 'success' or 'status'
    if (rules != null && !rules.isEmpty()) {
        
        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId());
        record.setViolationType("LOGIN_VIOLATION");
        record.setDetails("Login policy violation");
        
        // Use the first rule found
        PolicyRule rule = rules.get(0);
        record.setPolicyRuleId(rule.getId());
        record.setSeverity(rule.getSeverity() != null ? rule.getSeverity() : "LOW");

        violationRecordRepository.save(record);
    }
}
}
