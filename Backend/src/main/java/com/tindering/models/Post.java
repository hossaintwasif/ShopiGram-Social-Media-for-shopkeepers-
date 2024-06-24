package com.tindering.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(unique = true)
	private Integer postid;
	private String caption;
	private String Image;
	private String video;

	@OneToMany
	private List<Comment> comments=new ArrayList<>();
	
	@ManyToOne
	//@JsonIgnore
	private User user;
	
	@ManyToMany
	//@JsonIgnore
	private List<User> liked=new ArrayList<>();
	private LocalDateTime createAt;

	public Integer getPostid() {
		return postid;
	}

	public void setPostid(Integer postid) {
		this.postid = postid;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Post() {

	}

	public Post(Integer postid, String caption, String image, String video, List<Comment> comments, User user, List<User> liked, LocalDateTime createAt) {
		this.postid = postid;
		this.caption = caption;
		Image = image;
		this.video = video;
		this.comments = comments;
		this.user = user;
		this.liked = liked;
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Post{" +
				"postid=" + postid +
				", caption='" + caption + '\'' +
				", Image='" + Image + '\'' +
				", video='" + video + '\'' +
				", comments=" + comments +
				", user=" + user +
				", liked=" + liked +
				", createAt=" + createAt +
				'}';
	}
}
