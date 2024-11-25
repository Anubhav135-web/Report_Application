package com.jrtp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrtp.binding.CreatePost;
import com.jrtp.entity.Comment;
import com.jrtp.entity.Post;
import com.jrtp.service.CommentService;
import com.jrtp.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	@Autowired
	PostService service;
	@Autowired
	CommentService commentService;
	@Autowired
	HttpSession session;

	@PostMapping("/createpost")
	public String createPost(CreatePost post, Model model) {
		Integer UserId = (Integer) session.getAttribute("userId");

		if (UserId == null) {
			// User not logged in, redirect to login page
			model.addAttribute("message", "Please login to create a post.");
			return "login"; // Redirect to login page
		}

		String status = service.createPost(post, UserId);

		if (status.equalsIgnoreCase("post created successfully")) {
			model.addAttribute("message", "Post created successfully!");
			model.addAttribute("postObj", new CreatePost());
			return "createpost"; // Show create post page with success message
		} else {
			model.addAttribute("message", "Problem occurred while creating the post.");
			model.addAttribute("postObj", post);
			return "createpost"; // Show create post page with error message
		}
	}

	@GetMapping("/createpost")
	public String loadCreatePostPage(Model model) {
		model.addAttribute("postObj", new CreatePost());
		return "createpost";
	}

	@GetMapping("/dashboard")
	public String loadDashboard(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/login";
		}
		List<Post> posts = service.dashboard(userId);
		model.addAttribute("posts", posts);
		return "dashboard";
	}

	@GetMapping("/posts/search")
	public String searchPosts(@RequestParam("keyword") String keyword, Model model) {
		model.addAttribute("posts", service.searchPosts(keyword));
		return "index";
	}

	@GetMapping("/")
	public String viewAllPosts(Model model) {
		model.addAttribute("posts", service.getAllPosts());
		return "index";
	}

	@GetMapping("/posts/{id}")
	public String getPostDetails(@PathVariable("id") Integer id, Model model) {
		 Post post = service.getPostById(id);
		    if (post == null) {
		        return "redirect:/error"; // Handle case where post is not found
		    }

		    List<Comment> comments = commentService.getCommentsByPostId(id);
		    model.addAttribute("post", post);
		    model.addAttribute("comments", comments != null ? comments : new ArrayList<>()); // Pass empty list if null

		    return "post-details"; // Name of Thymeleaf template
	}

	@GetMapping("/posts")
	public String getAllPosts(Model model) {
	    Integer userId = (Integer) session.getAttribute("userId"); // Retrieve the logged-in user ID

	    if (userId == null) {
	        model.addAttribute("message", "Please log in to view your posts.");
	        return "redirect:/login"; // Redirect to login if no user is logged in
	    }

	    // Fetch posts for the logged-in user
	    List<Post> posts = service.getPostsByUserId(userId);
	    model.addAttribute("posts", posts);
	    return "all-posts"; // Corresponds to the Thymeleaf template
	}


}
