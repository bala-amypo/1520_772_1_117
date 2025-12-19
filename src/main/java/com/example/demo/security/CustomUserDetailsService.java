package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User {

    private Long userId;
    private String role;

    public CustomUserDetails(String username, Long userId, String role) {
        super(username, "", null); // Empty password as we don't need it after JWT authentication
        this.userId = userId;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }
}
