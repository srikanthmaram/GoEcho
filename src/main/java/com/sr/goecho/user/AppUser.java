package com.sr.goecho.user;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//
//@Entity
public class AppUser {
	
	//@Id
	private String username;
	private String firstname;
	private String lastname;
	private String user_mail;
	private String mobile;
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppUser(String username, String firstname, String lastname, String user_mail, String mobile) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.user_mail = user_mail;
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
