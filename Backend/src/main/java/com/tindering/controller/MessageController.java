package com.tindering.controller;

import com.tindering.models.Message;
import com.tindering.models.User;
import com.tindering.service.MessageService;
import com.tindering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/message/chat/{chatId}")
    public Message createMessage(@RequestBody Message message, @RequestHeader("Authorization") String jwt, @PathVariable("chatId") Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Message message1 = messageService.createMessage(user,chatId,message);
        return message1;
    }

    @GetMapping("/api/message/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt, @PathVariable("chatId") Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Message> message1 = messageService.findChatsMessages(chatId);
        return message1;
    }
}
