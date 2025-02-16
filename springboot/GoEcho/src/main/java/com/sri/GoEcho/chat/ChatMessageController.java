package com.sri.GoEcho.chat;

import com.sri.GoEcho.chatroom.ChatRoom;
import com.sri.GoEcho.user.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ChatMessageController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private ChatMessageService chatMessageService;

    public ChatMessageController(SimpMessagingTemplate simpMessagingTemplate, ChatMessageService chatMessageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatMessageService = chatMessageService;
    }
    @MessageMapping("/test")
    @SendTo("/topic/testmessages")
    public Message test(@Payload Message message)
    {
        System.out.println("hello received message to chat controller : "+message.getContent() );
        return message;

    }

    @MessageMapping("/chat")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        ChatMessage saveMessage = chatMessageService.saveMessage(chatMessage);
        System.out.println("Hello !, reached chat controller ..");
        simpMessagingTemplate.convertAndSend("/queue/test", "Test message");

        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(),"/queue/messages/"+chatMessage.getSenderId(),chatMessage);
        System.out.println("Message sent successfully to {}"+chatMessage.getReceiverId());

    }
    @PostMapping("/mymessages")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@RequestBody ChatRoom chatRoom)
    {
   return ResponseEntity.ok(chatMessageService.findChatMessages(chatRoom.getSenderId(),chatRoom.getReceiverId()));

    }


}
