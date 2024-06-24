package com.tindering.controller;

import com.tindering.models.Chat;
import com.tindering.models.User;
import com.tindering.repository.ChatRepository;
import com.tindering.request.CreateChatRequest;
import com.tindering.service.ChatService;
import com.tindering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {


    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest req) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);
        User user2=userService.findUserById(req.getUserId());
        Chat chat=chatService.createChat(reqUser,user2);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
        User user=userService.findUserByJwt(jwt);
        List<Chat> usersChat=chatService.findUsersChat(user.getId());
        return usersChat;
    }
}
