package com.example.demo.util;

import java.util.List;

import com.example.demo.entity.LoginEvent;
import com.example.demo.entity.PolicyRule;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.repository.ViolationRecordRepository;
import org.springframework.stereotype.Component;

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
            return;
        }

        List<PolicyRule> rules = policyRuleRepository.findAll();

        if (rules.isEmpty()) {
            return;
        }

        PolicyRule rule = rules.get(0);

        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId());
        record.setPolicyRuleId(rule.getId());
        record.setViolationType("LOGIN_VIOLATION");
        record.setSeverity(rule.getSeverity());
        record.setDetails("Login policy violated");

        violationRecordRepository.save(record);
    }
}
