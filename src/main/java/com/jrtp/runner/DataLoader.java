package com.jrtp.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jrtp.entity.CourseEntity;
import com.jrtp.entity.EnqStatusEntity;
import com.jrtp.repository.CourseRepo;
import com.jrtp.repository.EnqStatusRepo;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	CourseRepo courserepo;
	@Autowired
	EnqStatusRepo enqstatusrepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		courserepo.deleteAll();
		
		CourseEntity courseentity1 = new CourseEntity();
		courseentity1.setCourseName("java");
		CourseEntity courseentity2 = new CourseEntity();
		courseentity2.setCourseName("python");
		CourseEntity courseentity3 = new CourseEntity();
		courseentity3.setCourseName("DevOps");

		courserepo.saveAll(Arrays.asList(courseentity1, courseentity2, courseentity3));
		enqstatusrepo.deleteAll();
		EnqStatusEntity enq1 = new EnqStatusEntity();
		enq1.setStatusname("enrolled");
		EnqStatusEntity enq2 = new EnqStatusEntity();
		enq2.setStatusname("lost");
		EnqStatusEntity enq3 = new EnqStatusEntity();
		enq3.setStatusname("new");
		enqstatusrepo.saveAll(Arrays.asList(enq1, enq2, enq3));

	}

}
