package com.jrtp.entity;

import java.time.LocalDate;

import jakarta.annotation.Generated;
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer enquiryId;
	String  stuname;
	Integer phone;
	String mode;
	String course;
	String status;
	LocalDate createddade;
	LocalDate updatedate;
	
	//UserDtlsEntity usedtls;

}
