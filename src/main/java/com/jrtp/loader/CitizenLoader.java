package com.jrtp.loader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.repo.CitizenPlanRepo;
@Component
public class CitizenLoader implements ApplicationRunner {
    
	@Autowired
	private CitizenPlanRepo planrepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//repo.deleteAll();
		//Loading data
		CitizenPlanInfo c1=new CitizenPlanInfo();
		c1.setPlanname("cash");
		c1.setPlanstatus("approved");
		c1.setPlanstartdate(LocalDate.now());
		c1.setPlanenddate(LocalDate.now().plusMonths(6));
		c1.setCitizenname("cathy");
		c1.setGender("female");
		c1.setBeneficialamount(5000);
		
		CitizenPlanInfo c2=new CitizenPlanInfo();
		c2.setPlanname("food");
		c2.setPlanstatus("denied");
		c2.setDenialreason("have rental amount");
		c2.setCitizenname("jhon");
		c2.setGender("male");
		
		CitizenPlanInfo c3=new CitizenPlanInfo();
		c3.setPlanname("Employement");
		c3.setPlanstatus("terminated");
		c3.setTerminationreason("got government job");
		c3.setCitizenname("michel");
		c3.setGender("male");
		
		CitizenPlanInfo c4=new CitizenPlanInfo();
		c1.setPlanname("medical");
		c1.setPlanstatus("approved");
		c1.setPlanstartdate(LocalDate.now());
		c1.setPlanenddate(LocalDate.now().plusMonths(8));
		c1.setCitizenname("ruby");
		c1.setGender("female");
		c1.setBeneficialamount(10000);
		
		CitizenPlanInfo c5=new CitizenPlanInfo();
		c5.setPlanname("medical");
		c5.setPlanstatus("denied");
		c5.setPlanstartdate(LocalDate.now());
		c5.setPlanenddate(LocalDate.now().plusMonths(8));
		c5.setCitizenname("lawrence");
		c5.setGender("male");
		c5.setDenialreason("his health dangerous for salman");
		
		
		CitizenPlanInfo c6=new CitizenPlanInfo();
		c6.setPlanname("employment");
		c6.setPlanstatus("approved");
		c6.setPlanstartdate(LocalDate.now());
		c6.setPlanenddate(LocalDate.now().plusMonths(8));
		c6.setCitizenname("robert");
		c6.setGender("male");
		c6.setBeneficialamount(10000);
		
		CitizenPlanInfo c7=new CitizenPlanInfo();
		c7.setPlanname("food");
		c7.setPlanstatus("approved");
		c7.setPlanstartdate(LocalDate.now());
		c7.setPlanenddate(LocalDate.now().plusMonths(8));
		c7.setCitizenname("albert");
		c7.setGender("male");
		c7.setBeneficialamount(50000);
		
		List<CitizenPlanInfo>infolist=Arrays.asList(c1,c2,c3,c4,c5,c6,c7);
		
		planrepo.saveAll(infolist);
	}

}
