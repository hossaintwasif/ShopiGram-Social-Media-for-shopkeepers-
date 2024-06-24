package com.tindering.controller;

import java.net.http.HttpRequest;
import java.util.List;

import com.tindering.models.User;
import com.tindering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tindering.models.Post;
import com.tindering.repository.PostRepository;
import com.tindering.response.ApiResponse;
import com.tindering.service.PostService;

@RestController
public class PostController {

	@Autowired
	public PostRepository postRepository;
	
	@Autowired
	public PostService postService;
	@Autowired
	public UserService userService;
	
	//Create Post
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		Post createpost=postService.CreatePost(post, reqUser.getId());
		return new ResponseEntity<>(createpost,HttpStatus.ACCEPTED);
	}
	
	//Delete Post
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User requser= userService.findUserByJwt(jwt);
		String message = postService.deletePost(postId, requser.getId());
		ApiResponse response=new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	//Find post by Post Id
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdhandler(@PathVariable("postId") Integer postid) throws Exception{
		Post post=postService.findPostById(postid);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	//Find Post by User Id
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable("userId") Integer userId) throws Exception{
		List<Post> userPost=postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(userPost,HttpStatus.OK);
	}
	
	//Find All posts 
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		List<Post> allPost=postService.findAllPost();
		return new ResponseEntity<List<Post>>(allPost,HttpStatus.OK);
	}
	
	//Save post by user id using post id
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savePostHandler(@PathVariable("postId") Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User requser= userService.findUserByJwt(jwt);
		Post savePost=postService.savePost(postId, requser.getId());
		return new ResponseEntity<Post>(savePost,HttpStatus.ACCEPTED);
	}
	
	//Like post by User id in Post id
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable("postId") Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User requser= userService.findUserByJwt(jwt);
		Post likePost=postService.likePost(postId, requser.getId());
		return new ResponseEntity<Post>(likePost,HttpStatus.ACCEPTED);
	}
}
