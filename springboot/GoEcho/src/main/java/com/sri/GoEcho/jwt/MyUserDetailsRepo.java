package com.sri.GoEcho.jwt;

import com.sri.GoEcho.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserDetailsRepo extends JpaRepository<AppUser,Integer> {
    AppUser findByAppUserEmail(String username);
}
