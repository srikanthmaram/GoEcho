package com.sri.GoEcho.jwt;


import com.sri.GoEcho.jwt.MyUserDetails;
import com.sri.GoEcho.jwt.MyUserDetailsRepo;
import com.sri.GoEcho.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserDetailsRepo myUserDetailsRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=myUserDetailsRepo.findByAppUserEmail(username);
        if(appUser==null)
        {

            throw new UsernameNotFoundException("User not found");
        }
        else
        {


return new MyUserDetails(appUser);
        }

    }



}
