package com.tindering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer messageId;
    private String content;
    private String image;

    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Chat chat;

    private LocalDateTime timestamp;

    public Message(Integer messageId, String content, String image, User user, Chat chat, LocalDateTime timestamp) {
        this.messageId = messageId;
        this.content = content;
        this.image = image;
        this.user = user;
        this.chat = chat;
        this.timestamp = timestamp;
    }

    public Message() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", user=" + user +
                ", chat=" + chat +
                ", timestamp=" + timestamp +
                '}';
    }
}
