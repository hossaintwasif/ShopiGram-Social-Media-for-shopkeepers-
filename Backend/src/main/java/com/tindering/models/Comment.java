package com.tindering.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CommentId;

    private String content;
    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> CommentLike = new ArrayList<>();

    private LocalDateTime createdAt;

    public Integer getCommentId() {
        return CommentId;
    }

    public void setCommentId(Integer commentId) {
        CommentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getCommentLike() {
        return CommentLike;
    }

    public void setCommentLike(List<User> commentLike) {
        CommentLike = commentLike;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Comment() {
    }

    public Comment(Integer commentId, String content, User user, List<User> commentLike, LocalDateTime createdAt) {
        CommentId = commentId;
        this.content = content;
        this.user = user;
        CommentLike = commentLike;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "CommentId=" + CommentId +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", CommentLike=" + CommentLike +
                ", createdAt=" + createdAt +
                '}';
    }
}
