package com.jrtp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jrtp.entity.CitizenPlanInfo;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlanInfo,Long> {
	@Query("select distinct(planname)from CitizenPlanInfo")
	public List<String>getPlanName();
	@Query("select distinct(planstatus)from CitizenPlanInfo")
     List<String>getPlanStatus();
	

}
