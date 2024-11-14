package com.jrtp.service;

import java.util.List;

import com.jrtp.binding.DashboardResponse;
import com.jrtp.binding.EnquiryForm;
import com.jrtp.binding.EnquirySearchCriteria;
import com.jrtp.binding.LoginForm;

public interface EnquiryService {
	public List<String>getCourseName();
	public List<String>getEnqStatus();
	public String addEnquiry(EnquiryForm enquiryform);
	public DashboardResponse getDashboardResponse(Integer userId);
	public List<EnquiryForm>getEnquiries(Integer UserId,EnquirySearchCriteria enquirysearchcriteria);
	public EnquiryForm editEnquiry(Integer userId);
	
	
	

}
