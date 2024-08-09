package com.jumpstartup.LoginBody;

import java.util.UUID;

public class LoginRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String hashpass;
    private String type;
    private String uuid;

    public LoginRequest(){
        this.uuid = UUID.randomUUID().toString();
    }

    public LoginRequest(String username,  String firstName, String lastName, String email, String hashpass, String type){
        this.username = username;
        this.email = email;
        this.hashpass = hashpass;
        this.type = type;
        this.uuid = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashpass() {
        return hashpass;
    }

    public void setHashpass(String hashpass) {
        this.hashpass = hashpass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
