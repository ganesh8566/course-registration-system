package com.student.registration.course_registration_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.registration.course_registration_system.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Custom query if needed, e.g., finding students by email
	boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);
	Optional<Student> findByEmail(String email);
}
