package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ViolationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId; 
    private long policyRuleId;
    private long eventId;
    private String voilationType;
    private String details;
    private String severity;
    private LocalDateTime detectedAt;
    private boolean resolved;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getPolicyRuleId() {
        return policyRuleId;
    }
    public void setPolicyRuleId(long policyRuleId) {
        this.policyRuleId = policyRuleId;
    }
    public long getEventId() {
        return eventId;
    }
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
    public String getVoilationType() {
        return voilationType;
    }
    public void setVoilationType(String voilationType) {
        this.voilationType = voilationType;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }
    public void setDetectedAt(LocalDateTime detectedAt) {
        this.detectedAt = detectedAt;
    }
    public boolean isResolved() {
        return resolved;
    }
    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
    public ViolationRecord(long id, long userId, long policyRuleId, long eventId, String voilationType, String details,
            String severity, boolean resolved) {
        this.id = id;
        this.userId = userId;
        this.policyRuleId = policyRuleId;
        this.eventId = eventId;
        this.voilationType = voilationType;
        this.details = details;
        this.severity = severity;
        this.resolved = resolved;
        this.detectedAt = LocalDateTime.now();
    }
    public ViolationRecord() {
    }
}
