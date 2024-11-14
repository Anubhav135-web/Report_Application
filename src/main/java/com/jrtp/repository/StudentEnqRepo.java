package com.jrtp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entity.*;
public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity,Integer> {

}
