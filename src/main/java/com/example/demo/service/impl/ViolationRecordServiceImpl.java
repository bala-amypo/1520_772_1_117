package com.example.demo.service.impl;

import com.example.demo.entity.ViolationRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ViolationRecordRepository;
import com.example.demo.service.ViolationRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViolationRecordServiceImpl implements ViolationRecordService {

    private final ViolationRecordRepository violationRepo;

    public ViolationRecordServiceImpl(ViolationRecordRepository violationRepo) {
        this.violationRepo = violationRepo;
    }

    @Override
    public ViolationRecord logViolation(ViolationRecord violation) {
        if (violation.getDetectedAt() == null) {
            violation.setDetectedAt(LocalDateTime.now());
        }
        if (violation.getResolved() == null) {
            violation.setResolved(false);
        }
        return violationRepo.save(violation);
    }

    @Override
    public ViolationRecord markResolved(Long id) {
        ViolationRecord record = violationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Violation not found"));
        record.setResolved(true);
        return violationRepo.save(record);
    }

    @Override
    public List<ViolationRecord> getViolationsByUser(Long userId) {
        return violationRepo.findByUserId(userId);
    }

    @Override
    public List<ViolationRecord> getUnresolvedViolations() {
        return violationRepo.findByResolvedFalse();
    }

    @Override
    public List<ViolationRecord> getAllViolations() {
        return violationRepo.findAll();
    }
}
