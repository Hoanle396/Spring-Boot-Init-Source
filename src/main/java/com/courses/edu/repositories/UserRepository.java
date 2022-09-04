package com.courses.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.edu.entities.Users;
import com.courses.edu.enums.Role;

public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);

	Users findByRole(Role role);
}