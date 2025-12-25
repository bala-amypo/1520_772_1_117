package com.example.demo.service.impl;

import com.example.demo.entity.PolicyRule;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;
import com.example.demo.service.ViolationRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {

    private final PolicyRuleRepository ruleRepo;
    private final ViolationRecordService violationService;

    // ✅ UPDATED CONSTRUCTOR
    public PolicyRuleServiceImpl(PolicyRuleRepository ruleRepo,
                                 ViolationRecordService violationService) {
        this.ruleRepo = ruleRepo;
        this.violationService = violationService;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {
        for (PolicyRule r : ruleRepo.findAll()) {
            if (r.getRuleCode().equals(rule.getRuleCode())) {
                throw new IllegalArgumentException("Rule code already exists");
            }
        }
        return ruleRepo.save(rule);
    }

    public void evaluateAndTriggerViolation(boolean violated, Long userId, String ruleCode) {
        if (violated) {
            ViolationRecord record = new ViolationRecord();
            record.setUserId(userId);
            record.setRuleCode(ruleCode);
            violationService.logViolation(record);
        }
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }

    @Override
    public Optional<PolicyRule> getRuleByCode(String ruleCode) {
        return ruleRepo.findAll().stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst();
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return ruleRepo.findAll();
    }
}
