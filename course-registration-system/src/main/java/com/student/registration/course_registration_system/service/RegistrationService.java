package com.student.registration.course_registration_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.registration.course_registration_system.entity.Course;
import com.student.registration.course_registration_system.entity.Registration;
import com.student.registration.course_registration_system.repository.CourseRepository;
import com.student.registration.course_registration_system.repository.RegistrationRepository;

@Service
public class RegistrationService {
	private final RegistrationRepository registrationRepository;
	private final CourseRepository courseRepository;

	public RegistrationService(RegistrationRepository registrationRepository, CourseRepository courseRepository) {
		this.registrationRepository = registrationRepository;
		this.courseRepository = courseRepository;
	}

	// Register a Course
	public Registration registerCourse(Long studentId, Long courseId) {
		if (registrationRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
			throw new RuntimeException("You are already registered for this course.");
		}

		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

		Registration registration = new Registration();
		registration.setStudentId(studentId);
		registration.setCourse(course);

		return registrationRepository.save(registration); // Fixed return type
	}

	// Remove Registration
	public void removeRegistration(Long studentId, Long courseId) {
		Registration registration = registrationRepository.findByStudentIdAndCourseId(studentId, courseId)
				.orElseThrow(() -> new RuntimeException("Registration not found"));

		registrationRepository.delete(registration);
	}

	// View Registered Courses
	public List<Course> viewRegisteredCourses(Long studentId) {
		List<Registration> registrations = registrationRepository.findByStudentId(studentId);
		return registrations.stream().map(Registration::getCourse) // Extract courses from registrations
				.toList();
	}

	// Search Courses by Keyword
	public List<Course> searchCourses(String keyword) {
		return courseRepository.findByTitleContaining(keyword);
	}
}