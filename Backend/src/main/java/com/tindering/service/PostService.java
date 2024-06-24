package com.tindering.service;

import java.util.List;

import com.tindering.models.Post;

public interface PostService {

	public Post CreatePost(Post post,Integer userid) throws Exception;
	
	public String deletePost(Integer postId,Integer userid) throws Exception;
	
	public List<Post> findPostByUserId(Integer userId) throws Exception;
	
	public Post findPostById(Integer postId) throws Exception;
	
	public List<Post> findAllPost();
	
	public Post savePost(Integer PostId,Integer userId) throws Exception;
	
	public Post likePost(Integer postId,Integer userId) throws Exception;
}
