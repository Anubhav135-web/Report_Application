package com.jrtp.binding;

public class SignUpForm {
	String username;
	String email;
	Long phone;
	
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public Long getphone() {
		return phone;
	}
	public void setphone(Long phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "SignUpForm [username=" + username + ", email=" + email + ", phone=" + phone + "]";
	}

}
