package com.jrtp.binding;

public class LoginForm{
	
	String password;
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginForm [password=" + password + ", email=" + email + "]";
	}

}
