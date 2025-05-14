package com.student.registration.course_registration_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.registration.course_registration_system.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Custom query method for admin authentication
    Optional<Admin> findByUsername(String username);
}
