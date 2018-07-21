package com.poisonedyouth.springSecurity.controller;

import com.poisonedyouth.springSecurity.domain.User;
import com.poisonedyouth.springSecurity.service.RoleService;
import com.poisonedyouth.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/home")
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable(value="userId") Long userId) {
		User user = userService.findUserById(userId);
		userService.deleteUser(user);
		return "admin";
	}
}
