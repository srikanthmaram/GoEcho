package com.sr.goecho.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.sr.goecho.model.Message;

@Controller
public class WebSocketController {
	
	

    @MessageMapping("/user/john")
    @SendTo("/topic/user/john/messages")
    public Message handleMessage_john(Message mymessage) {
    	
         System.out.println("Received message: " + mymessage);
         return mymessage;
    }
    @MessageMapping("/user/bob")
    @SendTo("/topic/user/bob/messages")
    public Message handleMessage_bob(Message mymessage) {
    	
        System.out.println("Received message: " + mymessage);
        return mymessage;
   }
    @MessageMapping("/user/CommonChatRoom")
    @SendTo("/topic/ChatRoom")
public Message handleMessage_CommonChatRoom(Message mymessage) {
    	
        System.out.println("Received Message : "+mymessage.getUsername());
        return mymessage;
   }
    
	
}

