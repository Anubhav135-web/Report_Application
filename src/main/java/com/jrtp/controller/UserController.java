package com.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrtp.binding.LoginForm;
import com.jrtp.binding.UserRegistration;
import com.jrtp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	HttpSession session;

	@PostMapping("/register")
	public String registerUser(UserRegistration register, Model model) {

		String status = service.RegisterUser(register, null);
		if (status.equalsIgnoreCase("success")) {

			model.addAttribute("userObj", new UserRegistration());
			return "success";
		} else {
			model.addAttribute("message", "you have already registered please login");
			model.addAttribute("userObj", new UserRegistration());
			return "register";
		}
	}

	@GetMapping("/register")
	public String loadRegistrationPage(Model model) {
		UserRegistration userR = new UserRegistration();
		model.addAttribute("userObj", userR);
		return "register";
	}

	@PostMapping("/login")
	public String loginUser(LoginForm form, Model model) {
		String status = service.loginUser(form, null);

		if ("success".equals(status)) {
			model.addAttribute("message", "Login successful");

			// Redirect to the intended page or default to createpost
			String redirectUrl = (String) session.getAttribute("redirectUrl");
			if (redirectUrl != null) {
				session.removeAttribute("redirectUrl");
				return "redirect:" + redirectUrl;
			}
			return "redirect:/dashboard";
		} else {
			model.addAttribute("message","Invalid email or password");
			return "login";
		}
	}

	@GetMapping("/login")
	public String loadLoginPage(Model model) {
		model.addAttribute("userObj", new LoginForm());
		return "login";

	}
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session to log the user out
        session.invalidate();

        // Redirect to the home page after logging out
        return "redirect:/";
    }

}
