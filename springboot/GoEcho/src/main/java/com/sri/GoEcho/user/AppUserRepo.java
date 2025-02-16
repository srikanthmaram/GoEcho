package com.sri.GoEcho.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Integer> {
    List<AppUser> findAllByStatus(Status status);
}
