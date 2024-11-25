package com.jrtp.service;

import com.jrtp.binding.LoginForm;
import com.jrtp.binding.UserRegistration;
import com.jrtp.entity.User;

public interface UserService {
	public String RegisterUser(UserRegistration userentity,Integer id);
    public String loginUser(LoginForm form,Integer UserId);
}
