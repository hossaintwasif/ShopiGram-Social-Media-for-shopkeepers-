package com.tindering.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tindering.models.Post;
import com.tindering.models.User;
import com.tindering.repository.PostRepository;
import com.tindering.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {
	
	@Autowired
	public PostRepository postRepository;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserRepository userRepository;

	@Override
	public Post CreatePost(Post post, Integer userid) throws Exception {
		
		User user = userService.findUserById(userid);
		
		Post newpost=new Post();
		newpost.setCaption(post.getCaption());
		newpost.setImage(post.getImage());
		newpost.setVideo(post.getVideo());
		newpost.setCreateAt(LocalDateTime.now());
		newpost.setUser(user);
		Post post1 = postRepository.save(newpost);
		return post1;
	}

	@Override
	public String deletePost(Integer postId, Integer userid) throws Exception {
		Post post=findPostById(postId);
		User user=userService.findUserById(userid);
		
		if(post.getUser().getId() != user.getId()) {
			throw new Exception("You have no access to delete this post!!!");
		}
		postRepository.delete(post);
		return "Your Post Deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId)  {
		return postRepository.findpostByUserid(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception{
		Optional<Post> optional=postRepository.findById(postId);
		if(optional.isEmpty()) {
			throw new Exception("Post not found with id "+postId);
		}
		return optional.get();
	}

	@Override
	public List<Post> findAllPost() {
		
		return postRepository.findAll();
	}

	@Override
	public Post savePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
		User user=userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
		User user=userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		return postRepository.save(post);
	}

}
