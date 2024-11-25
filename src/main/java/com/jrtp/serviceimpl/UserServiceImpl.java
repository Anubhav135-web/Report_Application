package com.jrtp.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.binding.LoginForm;
import com.jrtp.binding.UserRegistration;
import com.jrtp.entity.User;
import com.jrtp.repository.UserRepository;
import com.jrtp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userrepository;
	@Autowired
	HttpSession session;

	@Override
	public String RegisterUser(UserRegistration userregistration, Integer id) {

		User existingUser = userrepository.findByEmail(userregistration.getEmail());
		if (existingUser == null) {
			User user = new User();
			user.setFname(userregistration.getFname());
			user.setLname(userregistration.getLname());
			user.setEmail(userregistration.getEmail());
			user.setPassword(userregistration.getPassword());
			userrepository.save(user);
			return "success";

		} else {
			return "error";
		}

	}

	@Override
	public String loginUser(LoginForm form, Integer userId) {
	    User existingUser = userrepository.findByEmailAndPassword(form.getEmail(), form.getPassword());
	    if (existingUser == null) {
	        return "error";
	    } else {
	        session.setAttribute("userId", existingUser.getUserId());
	        return "success";
	    }
	}

		
		
	}


