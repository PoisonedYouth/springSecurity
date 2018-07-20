package com.poisonedyouth.springSecurity.service;

import com.poisonedyouth.springSecurity.domain.Role;
import com.poisonedyouth.springSecurity.domain.User;

import java.util.List;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
	List<User> findAllUsersByRole(Role role);
}
