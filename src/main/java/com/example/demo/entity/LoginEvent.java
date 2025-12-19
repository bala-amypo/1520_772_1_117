package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoginEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String ipAddress; 
    private String location;
    private String deviceId;
    private LocalDateTime timestamp ;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private login loginStatus;
    public enum login{
        SUCCESS, FAILED
    }
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
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public login getLoginStatus() {
        return loginStatus;
    }
    public void setLoginStatus(login loginStatus) {
        this.loginStatus = loginStatus;
    }
    public LoginEvent(long id, long userId, String ipAddress, String location, String deviceId, login loginStatus) {
        this.id = id;
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.location = location;
        this.deviceId = deviceId;
        this.loginStatus = loginStatus;
        this.timestamp = LocalDateTime.now();
    }
    public LoginEvent() {
    }
}
