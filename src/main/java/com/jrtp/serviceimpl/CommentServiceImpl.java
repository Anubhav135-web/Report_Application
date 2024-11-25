package com.jrtp.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.binding.CreateComment;
import com.jrtp.entity.Comment;
import com.jrtp.entity.Post;
import com.jrtp.repository.CommentRepository;
import com.jrtp.repository.PostRepository;
import com.jrtp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

//	@Override
//	public String saveComment(Integer postId, CreateComment createComment) {
//		Optional<Post> postOptional = postRepository.findById(postId);
//		if (!postOptional.isPresent()) {
//			return "Post not found";
//		}
//		Post post = postOptional.get();
//
//		Comment comment = new Comment();
//		comment.setName(createComment.getName());
//		comment.setEmail(createComment.getEmail());
//		comment.setContent(createComment.getContent());
//		comment.setPost(post);
//		commentRepository.save(comment);
//
//		return "Comment added successfully";
//	}
	public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

	@Override
	public List<Comment> getCommentsByPostId(Integer postId) {
		return commentRepository.findByPost_PostId(postId);
	}

	@Override
	public List<Comment> getAllComments() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}
}
