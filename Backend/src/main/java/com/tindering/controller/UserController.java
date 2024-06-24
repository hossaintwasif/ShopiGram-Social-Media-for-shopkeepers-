package com.tindering.controller;


import java.util.List;


import com.tindering.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tindering.models.User;
import com.tindering.repository.UserRepository;
import com.tindering.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserService userService;
	
	
	
	
	@GetMapping("/api/users")
	public List<User> getuser(){
		
		List<User> user=userRepository.findAll();
		return user;
	}
	
	
	//Find User using user service class 
	@GetMapping("/api/users/{userid}")
	public User userid(@PathVariable("userid") Integer userid) throws UserException {
		
		User finduser=userService.findUserById(userid);
		return finduser;
	}
	
	
	//Update user using user service class
	@PutMapping("/api/users")
	public User updateuser(@RequestHeader("Authorization") String jwt,@RequestBody User user) throws UserException {
		User CurrentUser = userService.findUserByJwt(jwt);
		User userupdate=userService.updateuser(user, CurrentUser.getId());
		return userupdate;
		
	}
	
	
	//Follower and following user 
	@PutMapping("/api/users/follow/{following}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable("following") Integer following) throws UserException {
		User follower = userService.findUserByJwt(jwt);
		User user = userService.followUser(follower.getId(), following);
		return user;
	}
	
	//Search user as per first , last name and email id
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		List<User> users=userService.searchUser(query);
		return users;
	}

	//User detals
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt){
		System.out.println("JWT---"+jwt);
		User user= userService.findUserByJwt(jwt);
		return user;
	}

}
