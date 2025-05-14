package com.student.registration.course_registration_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.student.registration.course_registration_system.entity.Admin;
import com.student.registration.course_registration_system.entity.Course;
import com.student.registration.course_registration_system.repository.AdminRepository;
import com.student.registration.course_registration_system.repository.CourseRepository;

@Service
public class AdminService {
    private final CourseRepository courseRepository;
    private final AdminRepository adminRepository;

    // Combined Constructor for Dependency Injection
    public AdminService(CourseRepository courseRepository, AdminRepository adminRepository) {
        this.courseRepository = courseRepository;
        this.adminRepository = adminRepository;
    }

    // Method to authenticate admin
//    public boolean authenticateAdmin(String username, String password) {
//        Optional<Admin> admin = adminRepository.findByUsername(username);
//
//        if (admin.isPresent()) {
//            // Verify password (encryption logic can be added here)
//            return admin.get().getPassword().equals(password);
//        }
//
//        return false;
//    }
    
    public boolean authenticateAdmin(String username, String password) {
        // Example logic: Check username and password from the database
        Optional<Admin> admin = adminRepository.findByUsername(username);
        return admin.isPresent() && admin.get().getPassword().equals(password);
    }

    // Add a course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    // View all courses
    public List<Course> viewCourses() {
        return courseRepository.findAll();
    }

    // Update a course
    public Course updateCourse(Long courseId, Course updatedCourse) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setCredits(updatedCourse.getCredits());

        return courseRepository.save(course);
    }

    // Delete a course
    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(courseId);
    }
}