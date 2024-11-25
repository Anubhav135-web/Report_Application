package com.jrtp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost_PostId(Integer postId);
}
