package com.sri.GoEcho.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage,Integer> {

    @Query("select cm from ChatMessage cm where cm.senderId=?1 and cm.receiverId=?2")
    List<ChatMessage> findChatMessages(String senderId, String receiverId);
}
