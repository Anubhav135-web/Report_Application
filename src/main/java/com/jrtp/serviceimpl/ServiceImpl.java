package com.jrtp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.repo.CitizenPlanRepo;
import com.jrtp.request.SearchRequest;
import com.jrtp.service.PlanService;

@Service
public class ServiceImpl implements PlanService {
	@Autowired
	private CitizenPlanRepo repo;

	@Override
	public List<String> getPlanName() {
		return repo.getPlanName();

	}

	@Override
	public List<String> getPlanStatus() {
		return repo.getPlanStatus();

	}
	

	@Override
	public List<CitizenPlanInfo> search(SearchRequest request) {
	    // Create a base query entity
	    CitizenPlanInfo entity = new CitizenPlanInfo();
	    
	     
	    if (request.getPlanname() != null && !request.getPlanname().isEmpty()) {
	        entity.setPlanname(request.getPlanname());
	    }
	    if (request.getPlanstatus() != null && !request.getPlanstatus().isEmpty()) {
	        entity.setPlanstatus(request.getPlanstatus());
	    }
	    if (request.getGender() != null && !request.getGender().isEmpty()) {
	        entity.setGender(request.getGender());
	    }
	    if (request.getPlanstartdate() != null) {
	        entity.setPlanstartdate(request.getPlanstartdate());
	    }
	    if (request.getPlanenddate() != null) {
	        entity.setPlanenddate(request.getPlanenddate());
	    }
	   
	    System.out.println("Search Entity: " + entity);

	    // Create ExampleMatcher that ignores null values and supports partial string matches
	    ExampleMatcher matcher = ExampleMatcher.matching()
	        .withIgnoreNullValues()
	        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Allow substring matches
	        .withIgnoreCase(); // Case insensitive matching

	    // Create the Example object based on the populated entity
	    Example<CitizenPlanInfo> example = Example.of(entity, matcher);
	    
	    // Use the repository to find all matching records
	    List<CitizenPlanInfo> results = repo.findAll(example);
	    
	    // Log the results for debugging
	    System.out.println("Results Size: " + results.size());
	    results.forEach(result -> System.out.println(result));

	    return results;
	}


	@Override
	public boolean exportExcel() {

		return false;
	}

	@Override
	public boolean exportPDF() {
		return false;
	}
}
