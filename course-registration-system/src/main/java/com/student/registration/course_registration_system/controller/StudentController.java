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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.registration.course_registration_system.entity.Course;
import com.student.registration.course_registration_system.entity.Student;
import com.student.registration.course_registration_system.service.RegistrationService;
import com.student.registration.course_registration_system.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	private final StudentService studentService;
	private final RegistrationService registrationService;

	// Combined Constructor for Dependency Injection
	public StudentController(StudentService studentService, RegistrationService registrationService) {
		this.studentService = studentService;
		this.registrationService = registrationService;
	}

	@PostMapping("/signin")
	public ResponseEntity<String> signInStudent(@RequestBody Student student) {
		try {
			boolean isAuthenticated = studentService.authenticateStudent(student.getEmail(), student.getPassword());
			if (isAuthenticated) {
				return ResponseEntity.ok("Login Successful");
			} else {
				return ResponseEntity.status(401).body("Invalid email or password");
			}
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signUpStudent(@RequestBody Student student) {
		try {
			studentService.signUpStudent(student);
			return ResponseEntity.ok("Sign up successful!");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id) {
		return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
		return new ResponseEntity<>(studentService.updateStudent(id, updatedStudent), HttpStatus.OK);
	}

	@PostMapping("/{studentId}/add-course")
	public ResponseEntity<String> addCourse(@PathVariable Long studentId, @RequestParam Long courseId) {
		try {
			registrationService.registerCourse(studentId, courseId);
			return ResponseEntity.ok("Course added successfully!");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{studentId}/delete-course")
	public ResponseEntity<String> deleteCourse(@PathVariable Long studentId, @RequestParam Long courseId) {
		try {
			registrationService.removeRegistration(studentId, courseId);
			return ResponseEntity.ok("Course deleted successfully!");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{studentId}/registered-courses")
	public ResponseEntity<List<Course>> viewRegisteredCourses(@PathVariable Long studentId) {
		return ResponseEntity.ok(registrationService.viewRegisteredCourses(studentId));
	}

	@GetMapping("/search-course")
	public ResponseEntity<List<Course>> searchCourse(@RequestParam String keyword) {
		return ResponseEntity.ok(registrationService.searchCourses(keyword));
	}
}