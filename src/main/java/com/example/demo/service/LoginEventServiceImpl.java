package com.example.demo.service.impl;

import com.example.demo.entity.LoginEvent;
import com.example.demo.repository.LoginEventRepository;
import com.example.demo.service.LoginEventService;
import com.example.demo.util.RuleEvaluationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginEventServiceImpl implements LoginEventService {

    private final LoginEventRepository loginRepo;
    private final RuleEvaluationUtil ruleEvaluator;

    public LoginEventServiceImpl(LoginEventRepository loginRepo, RuleEvaluationUtil ruleEvaluator) {
        this.loginRepo = loginRepo;
        this.ruleEvaluator = ruleEvaluator;
    }

    @Override
    public LoginEvent recordLogin(LoginEvent event) {
        // Validate required fields
        if (event.getIpAddress() == null || event.getDeviceId() == null) {
            throw new IllegalArgumentException("IP address and deviceId cannot be null");
        }

        // Set timestamp if missing
        if (event.getTimestamp() == null) {
            event.setTimestamp(LocalDateTime.now());
        }

        // Save login event
        LoginEvent savedEvent = loginRepo.save(event);

        // Trigger rule evaluation
        ruleEvaluator.evaluateLoginEvent(savedEvent);

        return savedEvent;
    }

    @Override
    public List<LoginEvent> getEventsByUser(Long userId) {
        return loginRepo.findByUserId(userId);
    }

    @Override
    public List<LoginEvent> getSuspiciousLogins(Long userId) {
        return loginRepo.findByUserIdAndLoginStatus(userId, "FAILED");
    }

    @Override
    public List<LoginEvent> getAllEvents() {
        return loginRepo.findAll();
    }
}
