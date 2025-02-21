package com.sri.GoEcho.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController

public class AppUserController {

@Autowired
private ActiveAppUsers activeAppUsers;

    @Autowired
    private AppUserService appUserService;


    @Autowired
    private AuthenticationManager authenticationManager;



    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }




    @GetMapping("/appusers")
    public Set<ActiveUser>  findConnectedUsers()
    {
        return activeAppUsers.getAllActivUsers();
    }




    @PostMapping("/login")
    public ResponseEntity<?> loginAppUser(@RequestBody AppUser appUser)
    {
        System.out.println("login requested....");

        return appUserService.verifyUser(appUser);
    }
    @PostMapping("/register")
    public  ResponseEntity<?> registerAppUser(@RequestBody AppUser appUser)
    {
return appUserService.saveAppUser(appUser);
    }
    @GetMapping("/test")
    public String temp()
    {
        return "heloo test connection successfullll";
    }



}
