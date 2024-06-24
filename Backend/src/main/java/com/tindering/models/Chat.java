package com.tindering.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chatId;
    private String image;
    private String chat_name;
    @ManyToMany
    private List<User> users=new ArrayList<>();
    private LocalDateTime timeStamp;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();


    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Chat() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Chat(Integer chatId, String image, String chat_name, List<User> users, LocalDateTime timeStamp, List<Message> messages) {
        this.chatId = chatId;
        this.image = image;
        this.chat_name = chat_name;
        this.users = users;
        this.timeStamp = timeStamp;
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", image='" + image + '\'' +
                ", chat_name='" + chat_name + '\'' +
                ", users=" + users +
                ", timeStamp=" + timeStamp +
                ", messages=" + messages +
                '}';
    }
}
