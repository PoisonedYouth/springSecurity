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
@RequestMapping("user/home")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@Valid User user, BindingResult bindingResult, Model model) {
		model.addAttribute("allRoles", roleService.getAllRoles());
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.saveUser(user);
		model.addAttribute("successMessage", "User has been registered successfully");
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.GET)
	public String showUser(@PathVariable(value="userId") Long userId, Model model) {
		model.addAttribute("allRoles", roleService.getAllRoles());
		model.addAttribute("user", userService.findUserById(userId));
		return "edituser";
	}
}
