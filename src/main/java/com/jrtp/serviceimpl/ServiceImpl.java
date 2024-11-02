package com.jrtp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.repo.CitizenPlanRepo;
import com.jrtp.request.SearchRequest;
import com.jrtp.service.PlanService;
@Service
public class ServiceImpl implements PlanService{
   @Autowired
   private CitizenPlanRepo repo;
	@Override
	public List<String> getPlanName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPlanStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CitizenPlanInfo> search(SearchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPDF() {
		// TODO Auto-generated method stub
		return false;
	}

}
