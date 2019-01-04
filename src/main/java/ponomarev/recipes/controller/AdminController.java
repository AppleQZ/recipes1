package ponomarev.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ponomarev.recipes.service.UserService;

import java.util.List;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin", method = RequestMethod.POST, params = "action=delete")
	public String DeleteUser(Model model, @RequestParam(name = "selectedUser", required = false) List<String> selectedUsers) {
		if (selectedUsers != null) {
			selectedUsers.forEach(selectedUser -> userService.deleteUser(selectedUser));
			return userService.isAuth(SecurityContextHolder.getContext()
					.getAuthentication()) ? "redirect:/logout" : admin(model);
		} else {
			return admin(model);
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST, params = "action=block")
	public String BlockUser(Model model, @RequestParam(name = "selectedUser", required = false) List<String> selectedUsers) {
		if (selectedUsers != null) {
			selectedUsers.forEach(selectedUser -> userService.blockUser(selectedUser));
			return userService.isBlock(SecurityContextHolder.getContext()
					.getAuthentication()) ? "redirect:/logout" : admin(model);
		} else {
			return admin(model);
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST, params = "action=unblock")
	public String UnblockUser(Model model, @RequestParam(name = "selectedUser", required = false) List<String> selectedUsers) {
		if (selectedUsers != null) {
			selectedUsers.forEach(selectedUser -> userService.unblockUser(selectedUser));
			return admin(model);
		} else {
			return admin(model);
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "admin";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST, params = "action=logout")
	public String Logout() {
		return "redirect:/logout";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST, params = "action=registration")
	public String Registration() {
		return "redirect:/registration";
	}
}
