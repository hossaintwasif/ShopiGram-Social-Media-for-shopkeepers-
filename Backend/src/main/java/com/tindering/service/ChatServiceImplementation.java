package com.tindering.service;

import com.tindering.models.Chat;
import com.tindering.models.User;
import com.tindering.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatServiceImplementation implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserService userService;

    @Override
    public Chat createChat(User reqUser, User user2) {

        //If the chat will already exit then we will return the old chat otherwise we create new chat
        Chat isExist = chatRepository.findChatByUsersId(user2,reqUser);

        if(isExist!=null){
            return isExist;
        }

        Chat chat=new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimeStamp(LocalDateTime.now());


        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat=chatRepository.findById(chatId);

        if(chat.isEmpty()){
            throw new Exception("Chat doesn't exit with this user id "+chatId);
        }

        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {

        return chatRepository.findByUsersId(userId);
    }
}
