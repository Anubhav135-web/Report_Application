package com.jrtp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrtp.binding.CreateComment;
import com.jrtp.entity.Comment;
import com.jrtp.entity.Post;
import com.jrtp.service.CommentService;
import com.jrtp.service.PostService;

@Controller
public class CommentController {
    
	@Autowired
	PostService service;
	@Autowired
	CommentService commentService;
	
	@GetMapping("/posts/{id}/comments")
	public String getPostComments(@PathVariable("id") Integer id, Model model) {
	    Post post = service.getPostById(id);
	    if (post == null) {
	        return "redirect:/error";  // Handle the error when post is not found
	    }
	    List<Comment> comments = commentService.getCommentsByPostId(id);
	    model.addAttribute("post", post);
	    model.addAttribute("comments", comments); // Fetch comments
	    return "post-details";
	}

//	@PostMapping("/posts/{id}/add-comment")
//	public String addComment(@PathVariable("id") Integer postId, CreateComment createComment) {
//	    commentService.saveComment(postId, createComment);
//	    return "redirect:/posts/" + postId; // Redirect back to the post details page
//	}
	
	@PostMapping("/posts/{id}/add-comment")
	public String addComment(@PathVariable("id") Integer postId, 
	                         @RequestParam("name") String name, 
	                         @RequestParam("email") String email, 
	                         @RequestParam("content") String content, 
	                         Model model) {

	    // Get the post object (optional but recommended to check)
	    Post post = service.getPostById(postId);
	    if (post == null) {
	        return "redirect:/error"; // Handle case where post is not found
	    }

	    // Create and save the comment
	    Comment newComment = new Comment();
	    newComment.setPost(post);
	    newComment.setName(name);
	    newComment.setEmail(email);
	    newComment.setContent(content);
	    

	    commentService.saveComment(newComment);

	    // Fetch updated comments list
	    List<Comment> comments = commentService.getCommentsByPostId(postId);
	    model.addAttribute("post", post);
	    model.addAttribute("comments", comments);

	    return "post-details"; // Return to the same page to display the updated comment list
	}
	@GetMapping("/comments")
	public String viewAllComments(Model model) {
	    // Fetch all comments from the database
	    List<Comment> allComments = commentService.getAllComments();
	    
	    // Add the comments to the model
	    model.addAttribute("comments", allComments);
	    
	    return "comments";  // Return the 'comments.html' page
	}


}
