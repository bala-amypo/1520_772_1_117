package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoginEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String ipAddress;
    private String location;
    private String deviceId;
    private LocalDateTime timestamp;
    private String loginStatus;

    public LoginEvent() {}

    public LoginEvent(Long id, Long userId, String ipAddress, String location, String deviceId, LocalDateTime timestamp, String loginStatus) {
        this.id = id;
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.location = location;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.loginStatus = loginStatus;
    }

    @PrePersist
    public void prePersist() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getIpAddress() { return ipAddress; }
    public String getLocation() { return location; }
    public String getDeviceId() { return deviceId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getLoginStatus() { return loginStatus; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public void setLocation(String location) { this.location = location; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setLoginStatus(String loginStatus) { this.loginStatus = loginStatus; }
}
