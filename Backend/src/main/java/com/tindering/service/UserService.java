package com.tindering.service;

import java.util.List;

import com.tindering.exceptions.UserException;
import com.tindering.models.User;

public interface UserService {

	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userid1, Integer userid2) throws UserException;
	
	public User updateuser(User user,Integer userid) throws UserException;
	
	public List<User> searchUser(String query);

	public User findUserByJwt(String jwt);
}
