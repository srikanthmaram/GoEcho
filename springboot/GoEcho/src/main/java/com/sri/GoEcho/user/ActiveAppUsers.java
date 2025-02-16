package com.sri.GoEcho.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ActiveAppUsers {


    private ConcurrentHashMap<String, ActiveUser> activeUsers = new ConcurrentHashMap<>();

    private SimpMessagingTemplate simpMessagingTemplate;


    public ActiveAppUsers(@Lazy SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;

    }
    public void sendUserList() {

        simpMessagingTemplate.convertAndSend("/topic/activeusers", getAllActivUsers());
    }
    public void addAppUser(String SessionId,ActiveUser activeUser)
    {


        activeUsers.put(SessionId,activeUser);

        sendUserList();

    }
    public void removeAppUser(String sessionId)
    {
        activeUsers.remove(sessionId);
        sendUserList();
    }
    public boolean isUserActive(ActiveUser activeUser){
        return activeUsers.containsValue(activeUser);
    }
    public Set<ActiveUser> getAllActivUsers()
    {
        return new HashSet<>(activeUsers.values());
    }

}
