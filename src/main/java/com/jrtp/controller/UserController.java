package com.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jrtp.binding.LoginForm;
import com.jrtp.binding.SignUpForm;
import com.jrtp.service.UserService;

import jakarta.mail.MessagingException;

@Controller
public class UserController {
	@Autowired
	UserService userservice;

	@GetMapping("/login")
	public String loginPage() {
		userservice.loginUser(new LoginForm());
		return "login";
	}

	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";

	}
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) throws MessagingException {
	    boolean satus = userservice.signUpUser(form);
	    
	    if (satus) {
	        model.addAttribute("SuccessMsg", "Email sent successfully.");
	    } else {
	        model.addAttribute("ErrorMsg", "Please use a unique email.");
	    }
	    return "signup";
	}


	@GetMapping("/unlock")
	public String unlockUser() {
		return "unlock";
	}

	@GetMapping("/dashboard")
	public String userDashboard() {
		return "dashboard";
	}

}
