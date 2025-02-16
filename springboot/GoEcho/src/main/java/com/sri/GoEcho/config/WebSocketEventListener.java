package com.sri.GoEcho.config;

import com.sri.GoEcho.user.ActiveAppUsers;
import com.sri.GoEcho.user.ActiveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import org.springframework.web.socket.messaging.SessionConnectEvent;

import org.springframework.web.socket.messaging.SessionDisconnectEvent;



@Component
public class WebSocketEventListener {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ActiveAppUsers activeAppUsers;







//    @EventListener
//    public void handleWebSocketConnectListener(SessionConnectEvent event)
//    {
//
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
//        String sessionId = accessor.getSessionId();
//        String username =  accessor.getSessionAttributes().toString();  // Retrieve username set in Handshake Interceptor
//        System.out.println("step 2 : event is fired....."+username);
//
//        if (username != null) {
//            // Add user to the connected users map
//            System.out.println("step 3 : event is fired....."+username);
//
//            System.out.println("user with sessionid "+sessionId + " username with "+username+"is added");
//            activeAppUsers.addAppUser(sessionId,new ActiveUser(username));
//            sendUserList();  // Broadcast updated user list
//        }
//
//    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
    {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();

        System.out.print("Disconnect request received: "+sessionId);
        activeAppUsers.removeAppUser(sessionId);
        sendUserList();  // Broadcast updated user list
    }
    private void sendUserList() {
        messagingTemplate.convertAndSend("/topic/activeusers", activeAppUsers.getAllActivUsers());
    }
}
