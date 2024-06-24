package com.tindering.request;

import com.tindering.models.User;

public class CreateChatRequest {


    private Integer userId;

    public CreateChatRequest(Integer userId) {
        this.userId = userId;
    }

    public CreateChatRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CreateChatRequest{" +
                "userId=" + userId +
                '}';
    }
}
