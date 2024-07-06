package com.sr.goecho.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
	private AppUserService appUserService;
	
	
	public AppUserController(AppUserService appUserService) {
		super();
		this.appUserService = appUserService;
	}


	@PostMapping("/createAppUser")
	public AppUser createAppUser(@RequestBody AppUser appuser)
	{
		return appUserService.saveAppUser(appuser);
	}
	@GetMapping("/fetchUsers")
	public List<AppUser>fetchUsers()
	{
		return appUserService.getAllUsers();
	}

}
