package com.jrtp.entity;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="CITIZEN_REPORT")
@Data
public class CitizenPlanInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long citizenid;
	private String citizenname;
	private String gender;
	private String planname;
	private String planstatus;
	private LocalDate planstartdate;
	private LocalDate planenddate;
	private Integer beneficialamount;
	private String denialreason;
	private String terminationreason;
	public  Long getCitizenid() {
		return citizenid;
	}
	public void setCitizenid(Long citizenid) {
		this.citizenid = citizenid;
	}
	public String getCitizenname() {
		return citizenname;
	}
	public void setCitizenname(String citizenname) {
		this.citizenname = citizenname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPlanname() {
		return planname;
	}
	public void setPlanname(String planname) {
		this.planname = planname;
	}
	public String getPlanstatus() {
		return planstatus;
	}
	public void setPlanstatus(String planstatus) {
		this.planstatus = planstatus;
	}
	public LocalDate getPlanstartdate() {
		return planstartdate;
	}
	public void setPlanstartdate(LocalDate planstartdate) {
		this.planstartdate = planstartdate;
	}
	public LocalDate getPlanenddate() {
		return planenddate;
	}
	public void setPlanenddate(LocalDate planenddate) {
		this.planenddate = planenddate;
	}
	public Integer getBeneficialamount() {
		return beneficialamount;
	}
	public void setBeneficialamount(Integer beneficialamount) {
		this.beneficialamount = beneficialamount;
	}
	public String getDenialreason() {
		return denialreason;
	}
	public void setDenialreason(String denialreason) {
		this.denialreason = denialreason;
	}
	public String getTerminationreason() {
		return terminationreason;
	}
	public void setTerminationreason(String terminationreason) {
		this.terminationreason = terminationreason;
	}
	
	@Override
	public String toString() {
		return "CitizenPlanInfo [citizenid=" + citizenid + ", citizenname=" + citizenname + ", gender=" + gender
				+ ", planname=" + planname + ", planstatus=" + planstatus + ", planstartdate=" + planstartdate
				+ ", planenddate=" + planenddate + ", beneficialamount=" + beneficialamount + ", denialreason="
				+ denialreason + ", terminationreason=" + terminationreason + ", getCitizenid()=" + getCitizenid()
				+ ", getCitizenname()=" + getCitizenname() + ", getGender()=" + getGender() + ", getPlanname()="
				+ getPlanname() + ", getPlanstatus()=" + getPlanstatus() + ", getPlanstartdate()=" + getPlanstartdate()
				+ ", getPlanenddate()=" + getPlanenddate() + ", getBeneficialamount()=" + getBeneficialamount()
				+ ", getDenialreason()=" + getDenialreason() + ", getTerminationreason()=" + getTerminationreason()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
