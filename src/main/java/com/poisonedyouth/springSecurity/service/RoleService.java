package com.poisonedyouth.springSecurity.service;

import com.poisonedyouth.springSecurity.domain.Role;
import com.poisonedyouth.springSecurity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}

	public Role findRoleById(int id) {
		return roleRepository.findById(id).get();
	}
}
