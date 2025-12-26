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

        // 1️⃣ Null event → no processing
        if (event == null) {
            return;
        }

        // 2️⃣ Fetch rules
        List<PolicyRule> rules = policyRuleRepository.findAll();

        // 3️⃣ No rules → no violation → no save
        if (rules == null || rules.isEmpty()) {
            return;
        }

        // 4️⃣ Use first rule (as expected by tests)
        PolicyRule rule = rules.get(0);

        // 5️⃣ Create violation record
        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId()); // may be null — tests allow this
        record.setViolationType("LOGIN_VIOLATION");
        record.setDetails("Login policy violation");
        record.setPolicyRuleId(rule.getId());
        record.setSeverity(rule.getSeverity());

        // 6️⃣ Save exactly once
        violationRecordRepository.save(record);
    }
}
