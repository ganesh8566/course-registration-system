package com.student.registration.course_registration_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.registration.course_registration_system.dto.AdminLoginRequest;
import com.student.registration.course_registration_system.entity.Course;
import com.student.registration.course_registration_system.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @PostMapping("/admin-login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginRequest request) {
        boolean isAuthenticated = adminService.authenticateAdmin(request.getUsername(), request.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("/admin-profile.html");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // Add a course
    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return new ResponseEntity<>(adminService.addCourse(course), HttpStatus.CREATED);
    }

    // View all courses
//    @GetMapping("/courses")
//    public ResponseEntity<List<Course>> viewCourses() {
//        return new ResponseEntity<>(adminService.viewCourses(), HttpStatus.OK);
//    }
    
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> viewCourses() {
        List<Course> courses = adminService.viewCourses(); // Ensure the service layer fetches the full course list
        return ResponseEntity.ok(courses);
    }

    // Update a course
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return new ResponseEntity<>(adminService.updateCourse(id, updatedCourse), HttpStatus.OK);
    }

    // Delete a course
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        adminService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully!", HttpStatus.NO_CONTENT);
    }
}
