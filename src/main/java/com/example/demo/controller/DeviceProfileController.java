package com.example.demo.controller;

import com.example.demo.entity.DeviceProfile;
import com.example.demo.service.DeviceProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceProfileController {

    private final DeviceProfileService deviceService;

    public DeviceProfileController(DeviceProfileService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public DeviceProfile register(@RequestBody DeviceProfile device) {
        return deviceService.registerDevice(device);
    }

    @PutMapping("/{id}/trust")
    public DeviceProfile updateTrust(@PathVariable Long id, @RequestParam boolean trust) {
        return deviceService.updateTrustStatus(id, trust);
    }

    @GetMapping("/user/{userId}")
    public List<DeviceProfile> byUser(@PathVariable Long userId) {
        return deviceService.getDevicesByUser(userId);
    }

    @GetMapping("/lookup/{deviceId}")
    public DeviceProfile lookup(@PathVariable String deviceId) {
        return deviceService.findByDeviceId(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));
    }
}
