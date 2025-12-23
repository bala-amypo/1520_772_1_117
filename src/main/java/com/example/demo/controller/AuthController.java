package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserAccount;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserAccountService userAccountService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody RegisterRequest request) {
        UserAccount user = new UserAccount(
                null,
                request.getEmployeeId(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getRole(),
                null,
                null
        );
        return userAccountService.createUser(user);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        UserAccount user = userAccountService.findByUsername(request.getUsernameOrEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new JwtResponse(token, user.getId(), user.getEmail(), user.getRole());
    }
}
