package com.student.registration.course_registration_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.registration.course_registration_system.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStudentId(Long studentId); // Get courses for a student

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId); // Prevent duplicate registrations
    Optional<Registration> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
