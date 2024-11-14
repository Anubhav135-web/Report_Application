package com.jrtp.binding;

public class EnquiryForm {
	Integer userid;
	
	String studentname;
	Long phone;
	String mode;
	String course;
	String status;
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "EnquiryForm [userid=" + userid + ", studentname=" + studentname + ", phone=" + phone + ", mode=" + mode
				+ ", course=" + course + ", status=" + status + "]";
	}
	

}
