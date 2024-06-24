package com.tindering.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer StoryId;

    @ManyToOne
    private User user;

    private String image;
    private String caption;
    private LocalDateTime timeStamp;

    public Integer getStoryId() {
        return StoryId;
    }

    public void setStoryId(Integer storyId) {
        StoryId = storyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Story() {
    }

    public Story(Integer storyId, User user, String image, String caption, LocalDateTime timeStamp) {
        StoryId = storyId;
        this.user = user;
        this.image = image;
        this.caption = caption;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Story{" +
                "StoryId=" + StoryId +
                ", user=" + user +
                ", image='" + image + '\'' +
                ", caption='" + caption + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
