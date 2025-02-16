package com.sri.GoEcho.chat;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {
    private ChatMessageRepo chatMessageRepo;

    public ChatMessageService(ChatMessageRepo chatMessageRepo) {
        this.chatMessageRepo = chatMessageRepo;
    }

    public ChatMessage saveMessage(ChatMessage chatMessage)
    {
        return chatMessageRepo.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId, String receiverId)
    {
        return chatMessageRepo.findChatMessages(senderId,receiverId);
    }
}
