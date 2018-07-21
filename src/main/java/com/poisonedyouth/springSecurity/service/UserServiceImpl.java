package com.poisonedyouth.springSecurity.service;

import com.poisonedyouth.springSecurity.domain.Role;
import com.poisonedyouth.springSecurity.domain.User;
import com.poisonedyouth.springSecurity.repositories.RoleRepository;
import com.poisonedyouth.springSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> findAllUsersByRole(Role role) {
		return userRepository.findAllByRole(role);
	}
}
