package com.sr.goecho.user;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
	ArrayList<TempUser> users=new ArrayList();
//	private AppUserService appUserService;
//	
//	
//	public AppUserController(AppUserService appUserService) {
//		super();
//		this.appUserService = appUserService;
//	}


//	@PostMapping("/createAppUser")
//	public AppUser createAppUser(@RequestBody AppUser appuser)
//	{
//		return appUserService.saveAppUser(appuser);
//	}
	@PostMapping("/fetchUsers")
	public ArrayList<TempUser> fetchUsers(@RequestBody TempUser username)
	{
		System.out.println("hellow welcome ");
		users.add(username);
		
		return users;
		
		
	}

}
