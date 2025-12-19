package com.example.demo.controller;

import com.example.demo.entity.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class PolicyRuleController {

    private final PolicyRuleService ruleService;

    public PolicyRuleController(PolicyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<PolicyRule> createRule(@RequestBody PolicyRule rule) {
        PolicyRule created = ruleService.createRule(rule);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PolicyRule> updateRule(@PathVariable Long id, @RequestBody PolicyRule updated) {
        PolicyRule rule = ruleService.updateRule(id, updated);
        return ResponseEntity.ok(rule);
    }

    @GetMapping("/active")
    public ResponseEntity<List<PolicyRule>> getActiveRules() {
        List<PolicyRule> activeRules = ruleService.getActiveRules();
        return ResponseEntity.ok(activeRules);
    }

    @GetMapping
    public ResponseEntity<List<PolicyRule>> getAllRules() {
        List<PolicyRule> rules = ruleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}
