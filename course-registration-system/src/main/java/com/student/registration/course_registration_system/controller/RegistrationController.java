package com.student.registration.course_registration_system.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.registration.course_registration_system.entity.Course;
import com.student.registration.course_registration_system.entity.Registration;
import com.student.registration.course_registration_system.service.RegistrationService;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // Register for a course
    @PostMapping
    public ResponseEntity<Registration> registerCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        return new ResponseEntity<>(registrationService.registerCourse(studentId, courseId), HttpStatus.CREATED);
    }

    // View registered courses for a student
    @GetMapping("/{studentId}")
    public ResponseEntity<List<Course>> viewRegisteredCourses(@PathVariable Long studentId) {
        return new ResponseEntity<>(registrationService.viewRegisteredCourses(studentId), HttpStatus.OK);
    }

    // Remove a course registration
    @DeleteMapping
    public ResponseEntity<String> removeRegistration(@RequestParam Long studentId, @RequestParam Long courseId) {
        registrationService.removeRegistration(studentId, courseId);
        return new ResponseEntity<>("Registration removed successfully!", HttpStatus.OK); // Changed status to OK
    }
}