package com.jrtp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByUser_UserId(Integer userId);
    
	List<Post> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    
}
