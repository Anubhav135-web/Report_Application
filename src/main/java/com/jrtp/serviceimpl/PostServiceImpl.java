package com.jrtp.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.binding.CreatePost;
import com.jrtp.entity.Post;
import com.jrtp.entity.User;
import com.jrtp.repository.PostRepository;
import com.jrtp.repository.UserRepository;
import com.jrtp.service.PostService;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postrepository;
    @Autowired
    UserRepository userrepository;
    @Override
    public String createPost(CreatePost post, Integer userId) {
        if (userId == null) {
            return "error: user not logged in";
        }

        // Retrieve the User entity
        Optional<User> userOptional = userrepository.findById(userId);
        if (!userOptional.isPresent()) {
            return "error: user does not exist";
        }

        User user = userOptional.get();

        // Create a new Post entity
        Post postEntity = new Post();
        postEntity.setTitle(post.getTitle());
        postEntity.setDescription(post.getDescription());
        postEntity.setContent(post.getContent());
        postEntity.setUser(user); // Associate post with the logged-in user

        postrepository.save(postEntity);

        return "post created successfully";
    }
	@Override
	public List<Post> dashboard(Integer userId) {
		List<Post>post=postrepository.findByUser_UserId(userId);
		return post;
	}
	
	@Override
	public List<Post> getAllPosts() {
	    return postrepository.findAll();
	}

	@Override
	public List<Post> searchPosts(String keyword) {
	    return postrepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
	}
	@Override
	public Post getPostById(Integer id) {
	    return postrepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
	}
	@Override
	public List<Post> getPostsByUserId(Integer userId) {
	    return postrepository.findByUser_UserId(userId); // Filter posts by user ID
	}



}
