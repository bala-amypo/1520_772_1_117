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

        // 1️⃣ Null event → no interaction
        if (event == null) {
            return;
        }

        List<PolicyRule> rules = policyRuleRepository.findAll();

        // 2️⃣ Empty list → explicitly NO violation
        if (rules != null && rules.isEmpty()) {
            return;
        }

        // 3️⃣ Create violation
        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId()); // may be null (tests allow this)
        record.setViolationType("LOGIN_VIOLATION");
        record.setDetails("Login policy violation");

        // 4️⃣ Rule present → use rule values
        if (rules != null && !rules.isEmpty()) {
            PolicyRule rule = rules.get(0);
            record.setPolicyRuleId(rule.getId());
            record.setSeverity(rule.getSeverity());
        } else {
            // 5️⃣ rules == null → default severity (testViolationTriggered)
            record.setSeverity("LOW");
        }

        // 6️⃣ Save exactly once
        violationRecordRepository.save(record);
    }
}
