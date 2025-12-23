package com.example.demo.service.impl;

import com.example.demo.entity.DeviceProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DeviceProfileRepository;
import com.example.demo.service.DeviceProfileService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DeviceProfileServiceImpl implements DeviceProfileService {

    private final DeviceProfileRepository deviceRepo;

    public DeviceProfileServiceImpl(DeviceProfileRepository deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    @Override
    public DeviceProfile registerDevice(DeviceProfile device) {
        Optional<DeviceProfile> existing = deviceRepo.findByDeviceId(device.getDeviceId());
        if (existing.isPresent() && existing.get().getUserId().equals(device.getUserId())) {
            throw new IllegalArgumentException("Device already registered for user");
        }
        device.setLastSeen(LocalDateTime.now());
        return deviceRepo.save(device);
    }

    @Override
    public DeviceProfile updateTrustStatus(Long id, boolean trust) {
        DeviceProfile device = deviceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        device.setIsTrusted(trust);
        device.setLastSeen(LocalDateTime.now());
        return deviceRepo.save(device);
    }

    @Override
    public List<DeviceProfile> getDevicesByUser(Long userId) {
        return deviceRepo.findByUserId(userId);
    }

    @Override
    public Optional<DeviceProfile> findByDeviceId(String deviceId) {
        return deviceRepo.findByDeviceId(deviceId);
    }
}
