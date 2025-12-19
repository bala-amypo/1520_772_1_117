package com.example.demo.controller;

import com.example.demo.entity.LoginEvent;
import com.example.demo.service.LoginEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logins")
public class LoginEventController {

    private final LoginEventService loginService;

    public LoginEventController(LoginEventService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/record")
    public LoginEvent record(@RequestBody LoginEvent event) {
        return loginService.recordLogin(event);
    }

    @GetMapping("/user/{userId}")
    public List<LoginEvent> byUser(@PathVariable Long userId) {
        return loginService.getEventsByUser(userId);
    }

    @GetMapping("/suspicious/{userId}")
    public List<LoginEvent> suspicious(@PathVariable Long userId) {
        return loginService.getSuspiciousLogins(userId);
    }

    @GetMapping
    public List<LoginEvent> all() {
        return loginService.getAllEvents();
    }
}
