package com.jrtp.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface PlanService {
	public List<String> getPlanName();

	public List<String> getPlanStatus();

	public List<CitizenPlanInfo> search(SearchRequest request);

	public void exportExcel(HttpServletResponse responce) throws Exception;

	public void exportPDF(HttpServletResponse responce) throws Exception;

	

        
}

