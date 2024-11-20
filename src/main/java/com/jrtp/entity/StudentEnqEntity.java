package com.jrtp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="STU_ENQ_TABLE")
public class StudentEnqEntity {
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer enquiryId;
	String  studentName;
	Long phone;
	String mode;
	String course;
	String status;
	@Column(name = "created_date")
	private LocalDate createdDate;
	LocalDate updatedate;
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false)
	UserDtlsEntity userdtlsentity;
	
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getStudentname() {
		return studentName;
	}
	public void setStuname(String stuname) {
		this.studentName = stuname;
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
	
	public LocalDate getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(LocalDate updatedate) {
		this.updatedate = updatedate;
	}
	public UserDtlsEntity getUserdtlsentity() {
		return userdtlsentity;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate localDate) {
		this.createdDate = localDate;
	}
	public void setUserdtlsentity(UserDtlsEntity userdtlsentity) {
		this.userdtlsentity = userdtlsentity;
	}
	@Override
	public String toString() {
		return "StudentEnqEntity [enquiryId=" + enquiryId + ", studentName=" + studentName + ", phone=" + phone
				+ ", mode=" + mode + ", course=" + course + ", status=" + status + ", createdDate=" + createdDate
				+ ", updatedate=" + updatedate + ", userdtlsentity=" + userdtlsentity + "]";
	}
	

}
