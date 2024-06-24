package com.tindering.repository;

import com.tindering.models.Chat;
import com.tindering.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    public List<Message> findByChatChatId(Integer chatId);

}
