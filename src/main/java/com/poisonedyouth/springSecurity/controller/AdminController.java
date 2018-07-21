package com.poisonedyouth.springSecurity.controller;

import com.poisonedyouth.springSecurity.domain.User;
import com.poisonedyouth.springSecurity.service.RoleService;
import com.poisonedyouth.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "admin/home/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable(value="userId") Long userId) {
		User user = userService.findUserById(userId);
		userService.deleteUser(user);
		return "redirect:/admin/home";
	}

	@RequestMapping(value = "admin/home/editUser/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable(value="userId") Long userId) {
		User user = userService.findUserById(userId);
		userService.saveUser(user);
		return "admin";
	}

	@RequestMapping(value = "admin/home/newUser", method = RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "newUser";
	}

	@RequestMapping(value = "admin/home/newUser", method = RequestMethod.POST)
	public String newUser(@Valid User user, BindingResult bindingResult, Model model) {
		model.addAttribute("allRoles", roleService.getAllRoles());
		if (bindingResult.hasErrors()) {
			return "newUser";
		}
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
			return "newUser";
		}
		userService.saveUser(user);
		return "redirect:/admin/home";
	}
}
