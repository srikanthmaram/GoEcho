package com.sri.GoEcho.user;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserTrackingService {
    private final Set<String> connectedUsers = Collections.synchronizedSet(new HashSet<>());


    public void addUser(String userId) {
        connectedUsers.add(userId);
    }


    public void removeUser(String userId) {
        connectedUsers.remove(userId);
    }


    public Set<String> getConnectedUsers() {
        return new HashSet<>(connectedUsers);
    }
}
