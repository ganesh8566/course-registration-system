package com.student.registration.course_registration_system.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.student.registration.course_registration_system.entity.Student;
import com.student.registration.course_registration_system.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Student signUpStudent(Student student) {
		// Check for duplicate email
		if (studentRepository.existsByEmail(student.getEmail())) {
			throw new RuntimeException("Email is already registered.");
		}

		// Check for duplicate mobile number
		if (studentRepository.existsByMobile(student.getMobile())) {
			throw new RuntimeException("Mobile number is already registered.");
		}

		// Save student to the database
		return studentRepository.save(student);
	}
	

	    public boolean authenticateStudent(String email, String password) {
	        Optional<Student> student = studentRepository.findByEmail(email);
	        if (student.isPresent()) {
	            return student.get().getPassword().equals(password);
	        }
	        return false;
	    }
	

	// Add a student
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	// View student profile by ID
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
	}

	// Update student profile
	public Student updateStudent(Long id, Student updatedStudent) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

		student.setName(updatedStudent.getName());
		student.setEmail(updatedStudent.getEmail());
		student.setMobile(updatedStudent.getMobile());

		return studentRepository.save(student);
	}
}