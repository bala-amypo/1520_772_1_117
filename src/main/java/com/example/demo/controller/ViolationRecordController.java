package com.example.demo.controller;

import com.example.demo.entity.ViolationRecord;
import com.example.demo.service.ViolationRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/violations")
public class ViolationRecordController {

    private final ViolationRecordService violationService;

    public ViolationRecordController(ViolationRecordService violationService) {
        this.violationService = violationService;
    }

    @PostMapping
    public ResponseEntity<ViolationRecord> logViolation(@RequestBody ViolationRecord violation) {
        ViolationRecord created = violationService.logViolation(violation);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<ViolationRecord> markResolved(@PathVariable Long id) {
        ViolationRecord resolved = violationService.markResolved(id);
        return ResponseEntity.ok(resolved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ViolationRecord>> getViolationsByUser(@PathVariable Long userId) {
        List<ViolationRecord> violations = violationService.getViolationsByUser(userId);
        return ResponseEntity.ok(violations);
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<ViolationRecord>> getUnresolvedViolations() {
        List<ViolationRecord> unresolved = violationService.getUnresolvedViolations();
        return ResponseEntity.ok(unresolved);
    }

    @GetMapping
    public ResponseEntity<List<ViolationRecord>> getAllViolations() {
        List<ViolationRecord> violations = violationService.getAllViolations();
        return ResponseEntity.ok(violations);
    }
}
