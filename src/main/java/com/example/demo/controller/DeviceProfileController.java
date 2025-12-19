package com.example.demo.controller;

import com.example.demo.entity.DeviceProfile;
import com.example.demo.service.DeviceProfileService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DeviceProfile> registerDevice(@RequestBody DeviceProfile device) {
        DeviceProfile created = deviceService.registerDevice(device);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/trust")
    public ResponseEntity<DeviceProfile> updateTrustStatus(@PathVariable Long id, @RequestParam boolean trust) {
        DeviceProfile updated = deviceService.updateTrustStatus(id, trust);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceProfile>> getDevicesByUser(@PathVariable Long userId) {
        List<DeviceProfile> devices = deviceService.getDevicesByUser(userId);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/lookup/{deviceId}")
    public ResponseEntity<DeviceProfile> findByDeviceId(@PathVariable String deviceId) {
        DeviceProfile device = deviceService.findByDeviceId(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));
        return ResponseEntity.ok(device);
    }
}
