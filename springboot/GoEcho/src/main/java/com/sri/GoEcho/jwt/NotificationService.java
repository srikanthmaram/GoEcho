package com.sri.GoEcho.jwt;

import com.sri.GoEcho.user.ActiveAppUsers;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private SimpMessagingTemplate simpMessagingTemplate;
    private ActiveAppUsers activeAppUsers;

    public NotificationService(@Lazy SimpMessagingTemplate simpMessagingTemplate, ActiveAppUsers activeAppUsers) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.activeAppUsers=activeAppUsers;
    }
    public void sendUserList() {
        simpMessagingTemplate.convertAndSend("/app/activeusers", activeAppUsers.getAllActivUsers());
    }
}

