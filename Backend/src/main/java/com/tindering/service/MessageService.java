package com.tindering.service;

import com.tindering.models.Chat;
import com.tindering.models.Message;
import com.tindering.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message message) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
