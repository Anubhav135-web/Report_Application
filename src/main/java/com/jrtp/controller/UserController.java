package com.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrtp.binding.LoginForm;
import com.jrtp.binding.SignUpForm;
import com.jrtp.binding.UnlockForm;
import com.jrtp.service.UserService;

import jakarta.mail.MessagingException;

@Controller
public class UserController {
	@Autowired
	UserService userservice;

	@GetMapping("/login")
	public String loginPage(Model model) {
		
	    model.addAttribute("loginForm",new LoginForm());
		return "login";
	}
	@PostMapping("/login")
	public String loginPage(@ModelAttribute("loginForm")LoginForm loginform,Model model) {
		String status=userservice.loginUser(loginform);
		if(status.equalsIgnoreCase("success")){
			return "redirect:/dashboard";
		}else {
			model.addAttribute("errMsg", status);
		}
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
	public String unlockUser(@RequestParam String email, Model model) {
		UnlockForm unlockformobj = new UnlockForm();
		unlockformobj.setEmail(email);
		model.addAttribute("unlockformobj", unlockformobj);
		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockHandle(@ModelAttribute("unlockformobj") UnlockForm form, Model model) {

		System.out.println(form);
		
		if(form.getNewpasword().equals(form.getConfirmpasword())) {
		boolean status=userservice.unlockUser(form);
		if(status) {
			model.addAttribute("succMsg","Account created successfully");
		}else {
			model.addAttribute("errMsg1", "Given Temporary pass is incorrect");
		}
		}
		else {
			model.addAttribute("errMsg","newpasword and confirm pasword not match");
		}
		return "unlock";
	}

	@GetMapping("/dashboard")
	public String userDashboard() {
		return "dashboard";
	}
	@GetMapping("/forgot")
	public String forgotForm() {
		return "forgotPwd";
	}
	@PostMapping("/forgot")
	public String forgotPwd(@RequestParam("email") String email ,Model model){
		String status=userservice.forgotPassword(email);
		if(status.equalsIgnoreCase("Email does not exist")) {
			model.addAttribute("errMsg", status);
		}
		if(status.equalsIgnoreCase("success")) {
			model.addAttribute("succMsg"," password sent successfully,Please check your mail");
		}
		return "forgotPwd";
		
	}

}
