package com.tindering.service;

import com.tindering.models.Chat;
import com.tindering.models.Message;
import com.tindering.models.User;
import com.tindering.repository.ChatRepository;
import com.tindering.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService{

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message message) throws Exception {

        Chat chat = chatService.findChatById(chatId);
        Message createMsg = new Message();
        createMsg.setChat(chat);
        createMsg.setImage(message.getImage());
        createMsg.setContent(message.getContent());
        createMsg.setUser(user);
        createMsg.setTimestamp(LocalDateTime.now());
        Message savedMesssage = messageRepository.save(createMsg);

        chat.getMessages().add(savedMesssage);
        chatRepository.save(chat);

        return savedMesssage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatChatId(chatId);




    }
}
