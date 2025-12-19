package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PolicyRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ruleCode; 
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sev severty;
    private String conditionsJson;
    private boolean active;
    public enum Sev{
        LOW,MEDIUM,HIGH,CRITICAL
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getRuleCode() {
        return ruleCode;
    }
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Sev getSeverty() {
        return severty;
    }
    public void setSeverty(Sev severty) {
        this.severty = severty;
    }
    public String getConditionsJson() {
        return conditionsJson;
    }
    public void setConditionsJson(String conditionsJson) {
        this.conditionsJson = conditionsJson;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public PolicyRule(long id, String ruleCode, String description, Sev severty, String conditionsJson,
            boolean active) {
        this.id = id;
        this.ruleCode = ruleCode;
        this.description = description;
        this.severty = severty;
        this.conditionsJson = conditionsJson;
        this.active = active;
    }
    public PolicyRule() {
    }
}
