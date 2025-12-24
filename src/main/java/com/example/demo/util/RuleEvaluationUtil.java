package com.example.demo.util;

import com.example.demo.entity.ViolationRecord;
import com.example.demo.entity.LoginEvent;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.repository.ViolationRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class RuleEvaluationUtil {

    private final ViolationRecordRepository violationRecordRepository;

    public RuleEvaluationUtil(ViolationRecordRepository violationRecordRepository) {
        this.violationRecordRepository = violationRecordRepository;
    }

    public void evaluateLoginEvent(LoginEvent event) {

        ViolationRecord violation = new ViolationRecord();
        violation.setUserId(event.getUserId());
        violation.setViolationType("LOGIN_POLICY_VIOLATION");
        violation.setSeverity("HIGH");
        violation.setResolved(false);

        violationRecordRepository.save(violation);
    }
}
