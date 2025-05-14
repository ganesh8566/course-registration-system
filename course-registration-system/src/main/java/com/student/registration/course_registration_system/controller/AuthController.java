package com.student.registration.course_registration_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.registration.course_registration_system.dto.AdminLoginRequest;
import com.student.registration.course_registration_system.service.AdminService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AdminService adminService;

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint for admin login
    @PostMapping("/admin-login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginRequest request) {
        boolean isAuthenticated = adminService.authenticateAdmin(request.getUsername(), request.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
