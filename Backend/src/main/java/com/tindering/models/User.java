package com.tindering.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String password;
	
	private List<Integer> followers = new ArrayList<>();
	private List<Integer> followings = new ArrayList<>();
	
	@ManyToMany//(cascade = CascadeType.ALL,mappedBy = "user")
	@JsonIgnore
	private List<Post> savedPost = new ArrayList<>();
	
	
	
	
	public List<Post> getSavedPost() {
		return savedPost;
	}
	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Integer> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}
	public List<Integer> getFollowings() {
		return followings;
	}
	public void setFollowings(List<Integer> followings) {
		this.followings = followings;
	}
	public User(Integer id, String firstName, String lastName, String gender, String email, String password,
			List<Integer> followers, List<Integer> followings, List<Post> savedPost) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.followers = followers;
		this.followings = followings;
		this.savedPost = savedPost;
	}
	public User() {
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", followers=" + followers + ", followings="
				+ followings + ", savedPost=" + savedPost + "]";
	}
	
	
}
