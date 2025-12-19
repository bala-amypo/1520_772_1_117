package com.example.demo.service;

import com.example.demo.entity.ViolationRecord;

import java.util.List;

public interface ViolationRecordService {
    ViolationRecord logViolation(ViolationRecord violation);
    ViolationRecord markResolved(Long id);
    List<ViolationRecord> getViolationsByUser(Long userId);
    List<ViolationRecord> getUnresolvedViolations();
    List<ViolationRecord> getAllViolations();
}
