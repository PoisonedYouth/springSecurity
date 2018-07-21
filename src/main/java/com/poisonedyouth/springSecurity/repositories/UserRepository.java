package com.poisonedyouth.springSecurity.repositories;

import com.poisonedyouth.springSecurity.domain.Role;
import com.poisonedyouth.springSecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	List<User> findAllByRole(Role role);
}