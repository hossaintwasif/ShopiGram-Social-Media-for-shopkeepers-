package com.tindering.models;

import jakarta.persistence.*;

@Entity
public class Reels {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ReelId;

    private String title;
    private String video;

    @ManyToOne
    private User user;

    public Integer getReelId() {
        return ReelId;
    }

    public void setReelId(Integer reelId) {
        ReelId = reelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reels() {
    }

    public Reels(Integer reelId, String title, String video, User user) {
        ReelId = reelId;
        this.title = title;
        this.video = video;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reels{" +
                "ReelId=" + ReelId +
                ", title='" + title + '\'' +
                ", video='" + video + '\'' +
                ", user=" + user +
                '}';
    }
}
