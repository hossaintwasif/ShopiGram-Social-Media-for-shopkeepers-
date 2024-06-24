package com.tindering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tindering.config.JwtProvider;
import com.tindering.models.User;
import com.tindering.repository.UserRepository;
import com.tindering.request.LoginRequest;
import com.tindering.response.AuthResponse;
import com.tindering.service.CustomUserdetailService;
import com.tindering.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	public UserService userService;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	public CustomUserdetailService customUserdetailService;

	// Create user using User service class
	@PostMapping("/signup")
	public AuthResponse createuser(@RequestBody User user) throws Exception {

		User emailIsExist = userRepository.findByEmail(user.getEmail());

		if (emailIsExist != null) {
			throw new Exception("Account already exist with this " + emailIsExist + " email id...Try new Email id !!");
		}


		User newuser = new User();

		newuser.setEmail(user.getEmail());
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getLastName());
		newuser.setPassword(passwordEncoder.encode(user.getPassword()));
		newuser.setGender(user.getGender());

		User usercreate = userRepository.save(newuser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(usercreate.getEmail(),
				usercreate.getPassword());
		String token = JwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse(token, "Register success");

		return authResponse;
	}

	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginrequest) throws Exception {
		Authentication authentication = authentication(loginrequest.getEmail(), loginrequest.getPassword());
		String token = JwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse(token, "Singin successfully");

		return authResponse;
	}

	private Authentication authentication(String email, String password) throws Exception {
		UserDetails userDetails = customUserdetailService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new Exception("Invalid credentials ");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password Doesn't match !!");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
