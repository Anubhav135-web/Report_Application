package com.jrtp.service;

import org.springframework.ui.Model;

import com.jrtp.binding.LoginForm;
import com.jrtp.binding.SignUpForm;
import com.jrtp.binding.UnlockForm;

import jakarta.mail.MessagingException;

public interface UserService {
	public String loginUser(LoginForm loginform);
	public boolean signUpUser(SignUpForm signupform) throws MessagingException;
	public String unlockUser(UnlockForm unlockform);
	public String forgotPassword(String email);

}
