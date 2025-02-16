package com.sri.GoEcho.user;


import jakarta.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    private String appUserEmail;
    private String appUserPassword;
    @Enumerated(EnumType.STRING)
    private Status status;

    public AppUser() {
    }

    public AppUser(int appUserId, String appUserEmail, String appUserPassword, Status status) {
        this.appUserId = appUserId;
        this.appUserEmail = appUserEmail;
        this.appUserPassword = appUserPassword;
        this.status=status;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public String getAppUserEmail() {
        return appUserEmail;
    }

    public String getappUserPassword() {
        return appUserPassword;
    }

    public Status getStatus() {
        return status;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public void setAppUserEmail(String appUserEmail) {
        this.appUserEmail = appUserEmail;
    }

    public void setappUserPassword(String appUserPassword) {
        this.appUserPassword = appUserPassword;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", appUserEmail='" + appUserEmail + '\'' +
                ", appUserPassword='" + appUserPassword + '\'' +
                ", Status=" + status +
                '}';
    }
}
