package com.jrtp.service;

import java.util.List;

import com.jrtp.binding.CreatePost;
import com.jrtp.entity.Post;
public interface PostService {
	public String createPost(CreatePost post,Integer userId);
    public List<Post>dashboard(Integer userId);
    List<Post> getAllPosts();
    List<Post> searchPosts(String keyword);
    Post getPostById(Integer id);
    List<Post> getPostsByUserId(Integer userId);

}
