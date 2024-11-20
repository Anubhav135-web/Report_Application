package com.jrtp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AIT_USER_DTLS")
public class UserDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Only one auto_increment column
	private Integer userid;
	private String username;
	private String email;
	private Long phone;
	private String password;
	private String accstatus;
	@OneToMany(mappedBy="userdtlsentity" ,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<StudentEnqEntity>enquiries;
	// Getters and setters

	

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccstatus() {
		return accstatus;
	}

	public void setAccstatus(String accstatus) {
		this.accstatus = accstatus;
	}
	public List<StudentEnqEntity> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(List<StudentEnqEntity> enquiries) {
		this.enquiries = enquiries;
	}

}
