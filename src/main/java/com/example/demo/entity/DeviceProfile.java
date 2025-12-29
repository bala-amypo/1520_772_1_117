package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_profiles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DeviceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String deviceId;
    private String deviceType;
    private String osVersion;
    private LocalDateTime lastSeen;
    private Boolean isTrusted;

    public DeviceProfile() {
    }

    public DeviceProfile(
            Long id,
            Long userId,
            String deviceId,
            String deviceType,
            String osVersion,
            LocalDateTime lastSeen,
            Boolean isTrusted
    ) {
        this.id = id;
        this.userId = userId;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.osVersion = osVersion;
        this.lastSeen = lastSeen;
        this.isTrusted = isTrusted;
    }

    @PrePersist
    @PreUpdate
    public void updateLastSeen() {
        this.lastSeen = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public Boolean getIsTrusted() {
        return isTrusted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setIsTrusted(Boolean isTrusted) {
        this.isTrusted = isTrusted;
    }

    @JsonIgnore
    public Object getBody() {
        return this;
    }
}
