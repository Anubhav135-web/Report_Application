package com.jrtp.binding;

public class UnlockForm {
	String email;
	String tempasword;
	String newpasword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTempasword() {
		return tempasword;
	}
	public void setTempasword(String tempasword) {
		this.tempasword = tempasword;
	}
	public String getNewpasword() {
		return newpasword;
	}
	public void setNewpasword(String newpasword) {
		this.newpasword = newpasword;
	}
	@Override
	public String toString() {
		return "UnlockForm [email=" + email + ", tempasword=" + tempasword + ", newpasword=" + newpasword + "]";
	}
	

}
