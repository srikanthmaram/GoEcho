package com.sri.GoEcho.user;

import com.sri.GoEcho.jwt.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {


    @Autowired
    private  AppUserRepo appUserRepo;




    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }


    public AppUser disconnectAppUser(AppUser appUser)
    {
        appUser.setStatus(Status.OFFLINE);
        return appUserRepo.save(appUser);
    }

    public List<AppUser> findConnectedUsers()
    {
        return appUserRepo.findAllByStatus(Status.ONLINE);
    }


    public ResponseEntity<String> verifyUser(AppUser user) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getAppUserEmail(),user.getappUserPassword()));
        if(authentication.isAuthenticated()) {

            return ResponseEntity.ok(jwtService.generateToken(user.getAppUserEmail()));
        }
        return ResponseEntity.ok("Invalid User or Failed to Login");
    }


    public ResponseEntity<String> saveAppUser(AppUser appUser)
    {
        appUser.setappUserPassword(bCryptPasswordEncoder.encode(appUser.getappUserPassword()));
        appUserRepo.save(appUser);
        return ResponseEntity.ok("User Is Saved");
    }


}
