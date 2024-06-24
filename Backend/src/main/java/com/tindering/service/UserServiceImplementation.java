package com.tindering.service;

import java.util.List;
import java.util.Optional;

import com.tindering.config.JwtProvider;
import com.tindering.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tindering.models.User;
import com.tindering.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	public UserRepository userRepository;

	//Create or register user 
	@Override
	public User registerUser(User user) {
		User newuser=new User();
		newuser.setEmail(user.getEmail());
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getLastName());
		newuser.setPassword(user.getPassword());
		newuser.setGender(user.getGender());
		User usercreate = userRepository.save(newuser);
		
		return usercreate;
	}

	//Find user using user id
	@Override
	public User findUserById(Integer userid) throws UserException {
      Optional<User> user=userRepository.findById(userid);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("No User Exist"+userid);
	}

	//Find user using email id
	@Override
	public User findUserByEmail(String email) {
		User findByEmail = userRepository.findByEmail(email);
		return findByEmail;
	}
	

	//Follow user -> Follower is following the user 
	@Override
	public User followUser(Integer reqUserId, Integer following) throws UserException{
		User requser=findUserById(reqUserId);
		User following1=findUserById(following);

		requser.getFollowers().add(following1.getId());
		following1.getFollowings().add(requser.getId());
		
		userRepository.save(requser);
		userRepository.save(following1);
		
		return requser;
	}

	//Update user 
	@Override
	public User updateuser(User user,Integer userid) throws UserException{
      Optional<User> updateuser=userRepository.findById(userid);
		
		if(updateuser.isPresent()) {
			User olduser=updateuser.get();
			olduser.setFirstName(user.getFirstName());
			olduser.setLastName(user.getLastName());
			//olduser.setEmail(user.getEmail());
			//olduser.setGender(user.getGender());
			return this.userRepository.save(olduser);
		}else {
	        throw new UserException("User not found with ID: " + userid);
	    }
	}

	//Search user using firstname , lastname , email 
	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user=userRepository.findByEmail(email);
		return user;
	}

}
