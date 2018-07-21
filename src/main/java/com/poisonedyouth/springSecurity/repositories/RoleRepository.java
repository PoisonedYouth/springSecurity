package com.poisonedyouth.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poisonedyouth.springSecurity.domain.Role;
import org.springframework.stereotype.Repository;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);

}