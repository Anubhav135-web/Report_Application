package com.jrtp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entity.UserDtlsEntity;
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity,Integer> {
   public UserDtlsEntity findByEmail(String email);
}