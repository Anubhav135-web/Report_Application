package com.jrtp.service;

import java.util.List;

import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.request.SearchRequest;

public interface PlanService {
	public List<String>getPlanName();
	public List<String>getPlanStatus();
	public List<CitizenPlanInfo>search(SearchRequest request);
	public boolean exportExcel();
	public boolean exportPDF();

}
