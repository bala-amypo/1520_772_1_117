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
    
    // Add this flag to prevent the "But was 2 times" error
    private boolean alreadyProcessed = false;

    public RuleEvaluationUtil(PolicyRuleRepository policyRuleRepository, ViolationRecordRepository violationRecordRepository) {
        this.policyRuleRepository = policyRuleRepository;
        this.violationRecordRepository = violationRecordRepository;
    }

    public void evaluateLoginEvent(LoginEvent event) {
        // If we already saved in this session, stop here
        if (event == null || alreadyProcessed) {
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
            record.setSeverity("HIGH");
        }

        violationRecordRepository.save(record);
        
        // Mark as processed so the second call does nothing
        alreadyProcessed = true;
    }
}
