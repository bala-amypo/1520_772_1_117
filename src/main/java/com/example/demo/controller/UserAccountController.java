package com.example.demo.controller;

import com.example.demo.dto.UserAccountResponse;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService userService;

    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserAccountResponse> create(
            @RequestBody UserAccount user) {

        UserAccount savedUser = userService.createUser(user);

        UserAccountResponse response = new UserAccountResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getStatus()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponse> getById(@PathVariable Long id) {

        UserAccount user = userService.getUserById(id);

        UserAccountResponse response = new UserAccountResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<UserAccountResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        UserAccount user = userService.updateUserStatus(id, status);

        UserAccountResponse response = new UserAccountResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserAccountResponse>> getAll() {

        List<UserAccountResponse> users =
                userService.getAllUsers()
                        .stream()
                        .map(u -> new UserAccountResponse(
                                u.getId(),
                                u.getUsername(),
                                u.getEmail(),
                                u.getRole(),
                                u.getStatus()))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
}
