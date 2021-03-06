package com.poisonedyouth.springSecurity.controller;

import com.poisonedyouth.springSecurity.domain.User;
import com.poisonedyouth.springSecurity.service.RoleService;
import com.poisonedyouth.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = { "", "/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("allRoles", roleService.getAllRoles());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		model.addAttribute("allRoles", roleService.getAllRoles());
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
			return "registration";
		}
		userService.saveUser(user);
		model.addAttribute("successMessage", "User has been registered successfully");
		model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String adminHome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		final List<User> users = userService.findAllUsersByRole(roleService.findRoleById(2L));
		model.addAttribute("users", users);
		return  "admin";
	}

	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String userHome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addAttribute("user", user);
		return "user";
	}

}
