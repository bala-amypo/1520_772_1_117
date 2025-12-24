package com.example.demo.util;

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
