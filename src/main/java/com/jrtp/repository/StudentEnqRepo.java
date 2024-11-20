package com.jrtp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jrtp.binding.DashboardResponse;
import com.jrtp.entity.*;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity,Integer> {
	

}
