package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(
    name = "policy_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleCode")
    }
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private String severity;

    @Column(columnDefinition = "TEXT")
    private String conditionsJson;

    private Boolean active;

    public PolicyRule() {
    }

    public PolicyRule(
            Long id,
            String ruleCode,
            String description,
            String severity,
            String conditionsJson,
            Boolean active
    ) {
        this.id = id;
        this.ruleCode = ruleCode;
        this.description = description;
        this.severity = severity;
        this.conditionsJson = conditionsJson;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public String getSeverity() {
        return severity;
    }

    public String getConditionsJson() {
        return conditionsJson;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setConditionsJson(String conditionsJson) {
        this.conditionsJson = conditionsJson;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonIgnore
    public PolicyRule getBody() {
        return this;
    }
}
