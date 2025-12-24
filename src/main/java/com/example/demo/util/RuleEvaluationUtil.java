package com.example.demo.util;

import com.example.demo.entity.LoginEvent;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.ViolationRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class RuleEvaluationUtil {

    private final ViolationRecordRepository violationRecordRepository;

    public RuleEvaluationUtil(ViolationRecordRepository violationRecordRepository) {
        this.violationRecordRepository = violationRecordRepository;
    }

    public void evaluateLoginEvent(LoginEvent event) {
        ViolationRecord record = new ViolationRecord();
        record.setUserId(event.getUserId());
        record.setViolationType("LOGIN_VIOLATION");

        violationRecordRepository.save(record);
    }
}
