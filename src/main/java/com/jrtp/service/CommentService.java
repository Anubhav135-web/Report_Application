package com.jrtp.service;

import com.jrtp.binding.CreateComment;
import com.jrtp.entity.Comment;

import java.util.List;

public interface CommentService {
    public void saveComment(Comment comment);
    List<Comment> getCommentsByPostId(Integer postId);
	public List<Comment> getAllComments();
   
}
