package com.jrtp.service;

import java.util.List;

import com.jrtp.binding.DashboardResponse;
import com.jrtp.binding.EnquiryForm;
import com.jrtp.binding.EnquirySearchCriteria;
import com.jrtp.binding.LoginForm;
import com.jrtp.entity.StudentEnqEntity;

public interface EnquiryService {
	public List<String>getCourseName();
	public List<String>getEnqStatus();
	public String addEnquiry(EnquiryForm enquiryform);
	public DashboardResponse  getDashboard(Integer userid);
	public List<StudentEnqEntity>getEnquiries();
	public List<StudentEnqEntity>getfilteredenquiries(EnquirySearchCriteria criteria);
	public EnquiryForm editEnquiry(Integer userId);
	public EnquiryForm getEnquiryById(Integer enquiryId) ;
	
	

}
